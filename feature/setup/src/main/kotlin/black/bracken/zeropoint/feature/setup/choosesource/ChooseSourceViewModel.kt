package black.bracken.zeropoint.feature.setup.choosesource

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.data.kernel.model.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.model.PlayerId
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.uishare.ext.errorMessageResource
import black.bracken.zeropoint.uishare.util.StringResource
import black.bracken.zeropoint.util.TxMutex
import black.bracken.zeropoint.util.ext.valueIfMatchType
import black.bracken.zeropoint.util.withLockOn
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import black.bracken.zeropoint.resource.R as ResR

@HiltViewModel
class ChooseSourceViewModel @Inject constructor(
  private val localPrefRepository: LocalPrefRepository,
  private val valorantApiRepository: ValorantApiRepository,
) : ViewModel() {

  private val txMutex: TxMutex = TxMutex()

  @VisibleForTesting
  val rawUiState = MutableStateFlow<ChooseSourceUiState>(ChooseSourceUiState.Initial)
  val uiState by lazy {
    combine(
      rawUiState,
      txMutex.lockState,
    ) { uiState, inTransaction ->
      if (uiState is ChooseSourceUiState.Choose) {
        uiState.copy(
          inTransaction = inTransaction,
        )
      } else {
        uiState
      }
    }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ChooseSourceUiState.Initial,
      )
  }

  fun onChangeRiotId(riotId: String) {
    rawUiState.updateIfChoose { it.copy(modal = it.modal.copy(riotId = riotId)) }
  }

  fun onChangeTagline(tagline: String) {
    rawUiState.updateIfChoose { it.copy(modal = it.modal.copy(tagline = tagline)) }
  }

  fun onConfirmPlayerName() = txMutex.withLockOn(viewModelScope) {
    val snapshot = rawUiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return@withLockOn

    valorantApiRepository.getAccount(
      snapshot.modal.riotId,
      snapshot.modal.tagline,
    )
      .onSuccess { account ->
        localPrefRepository.setPlayerId(account.playerId)

        rawUiState.updateIfChoose { it.copy(modal = it.modal.copy(errorText = null)) }
        // TODO: transit
      }
      .onFailure { error ->
        rawUiState.updateIfChoose {
          it.copy(
            modal = it.modal.copy(
              errorText = when (error) {
                is ValorantApiRepository.Error.SerializationError -> StringResource(ResR.string.error_serialization)
                is ValorantApiRepository.Error.ApiError -> error.error.errorMessageResource
                else -> StringResource(ResR.string.error_unknown, error)
              },
            ),
          )
        }
      }
  }

  fun onClickRemoteButton() {
    rawUiState.updateIfChoose { it.copy(shouldOpenInputPlayerNameModal = true) }
  }

  fun onClickFakeButton() = txMutex.withLockOn(viewModelScope) {
    with(localPrefRepository) {
      setChosenApiDataSource(ChosenApiDataSource.FAKE)
      setPlayerId(PlayerId("fake-player-id"))
    }

    rawUiState.update { ChooseSourceUiState.RestartApp }
  }

  fun onCloseBottomSheet() {
    val snapshot = rawUiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      rawUiState.emit(snapshot.copy(shouldOpenInputPlayerNameModal = false))
    }
  }

  fun afterCloseBottomSheet() {
    val snapshot = rawUiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      if (snapshot.shouldOpenInputPlayerNameModal) {
        rawUiState.updateIfChoose { it.copy(shouldOpenInputPlayerNameModal = false) }
      }
    }
  }

  companion object {
    private fun MutableStateFlow<ChooseSourceUiState>.updateIfChoose(
      update: (ChooseSourceUiState.Choose) -> ChooseSourceUiState
    ) {
      val uiState = valueIfMatchType<ChooseSourceUiState.Choose>() ?: return
      update { update(uiState) }
    }
  }

}
