package black.bracken.zeropoint.feature.setup.choosesource

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.data.kernel.model.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.model.PlayerId
import black.bracken.zeropoint.data.kernel.model.error.ValorantApiError
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import javax.inject.Inject
import black.bracken.zeropoint.resource.R as ResR

@HiltViewModel
class ChooseSourceViewModel @Inject constructor(
  private val localPrefRepository: LocalPrefRepository,
  private val valorantApiRepository: ValorantApiRepository,
) : ViewModel() {

  private val txMutex: TxMutex = TxMutex()

  @VisibleForTesting
  val _uiState = MutableStateFlow<ChooseSourceUiState>(ChooseSourceUiState.Initial)
  val uiState by lazy {
    _uiState.createUiState(
      scope = viewModelScope,
      inTransactionFlow = txMutex.lockState,
    )
  }

  fun onChangeRiotId(riotId: String) {
    _uiState.updateIfChoose { it.copy(riotId = riotId) }
  }

  fun onChangeTagline(tagline: String) {
    _uiState.updateIfChoose { it.copy(tagline = tagline) }
  }

  fun onConfirmPlayerName() = txMutex.withLockOn(viewModelScope) {
    val snapshot = _uiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return@withLockOn

    _uiState.updateIfChoose { it.copy(isLoadingOnModal = true) }

    valorantApiRepository.getAccount(
      snapshot.riotId,
      snapshot.tagline,
    )
      .onSuccess { account ->
        localPrefRepository.setPlayerId(account.playerId)

        _uiState.updateIfChoose { it.copy(errorTextOnModal = null) }
        // TODO: transit
      }
      .onFailure { error ->
        _uiState.updateIfChoose {
          it.copy(
            errorTextOnModal = when (error) {
              is SerializationException -> StringResource(ResR.string.error_serialization)
              is ValorantApiError -> error.errorMessageResource
              else -> StringResource(ResR.string.error_unknown, error)
            }
          )
        }
      }

    _uiState.updateIfChoose { it.copy(isLoadingOnModal = false) }
  }

  fun onClickRemoteButton() {
    _uiState.updateIfChoose { it.copy(shouldOpenInputPlayerNameModal = true) }
  }

  fun onClickFakeButton() = txMutex.withLockOn(viewModelScope) {
    with(localPrefRepository) {
      setChosenApiDataSource(ChosenApiDataSource.FAKE)
      setPlayerId(PlayerId("fake-player-id"))
    }

    _uiState.update { ChooseSourceUiState.RestartApp }
  }

  fun onCloseBottomSheet() {
    val snapshot = _uiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      _uiState.emit(snapshot.copy(shouldOpenInputPlayerNameModal = false))
    }
  }

  fun afterCloseBottomSheet() {
    val snapshot = _uiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      if (snapshot.shouldOpenInputPlayerNameModal) {
        _uiState.updateIfChoose { it.copy(shouldOpenInputPlayerNameModal = false) }
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

    fun f() {}

    @VisibleForTesting
    fun MutableStateFlow<ChooseSourceUiState>.createUiState(
      scope: CoroutineScope,
      inTransactionFlow: Flow<Boolean>,
    ): StateFlow<ChooseSourceUiState> {
      return combine(
        this,
        inTransactionFlow,
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
          scope = scope,
          started = SharingStarted.Lazily,
          initialValue = ChooseSourceUiState.Initial,
        )
    }
  }

}
