package black.bracken.zeropoint.feature.setup.choosesource

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.data.kernel.domain.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.domain.PlayerId
import black.bracken.zeropoint.data.kernel.domain.error.ValorantApiException
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.uishare.ext.errorMessageResource
import black.bracken.zeropoint.uishare.util.StringResource
import black.bracken.zeropoint.util.ext.valueIfMatchType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import javax.inject.Inject
import black.bracken.zeropoint.resource.R as ResR

@HiltViewModel
class ChooseSourceViewModel @Inject constructor(
  private val localPrefRepository: LocalPrefRepository,
  private val valorantApiRepository: ValorantApiRepository,
) : ViewModel() {

  @VisibleForTesting
  val _uiState = MutableStateFlow<ChooseSourceUiState>(ChooseSourceUiState.Initial)
  val uiState get() = _uiState.asStateFlow()

  fun onChangeRiotId(riotId: String) {
    val snapshot = _uiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      _uiState.emit(snapshot.copy(riotId = riotId))
    }
  }

  fun onChangeTagline(tagline: String) {
    val snapshot = _uiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      _uiState.emit(snapshot.copy(tagline = tagline))
    }
  }

  fun onConfirmPlayerName() {
    val snapshot = _uiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      _uiState.emit(snapshot.copy(isLoadingOnModal = true))

      valorantApiRepository.getAccount(
        snapshot.riotId,
        snapshot.tagline,
      )
        .onSuccess { account ->
          localPrefRepository.setPlayerId(account.playerId)

          _uiState.emit(
            snapshot.copy(
              isLoadingOnModal = false,
              errorTextOnModal = null,
            )
          )
        }
        .onFailure { throwable ->
          _uiState.emit(
            snapshot.copy(
              isLoadingOnModal = false,
              errorTextOnModal = when (throwable) {
                is SerializationException -> StringResource(ResR.string.error_serialization)
                is ValorantApiException -> throwable.errorMessageResource
                else -> StringResource(ResR.string.error_unknown, throwable.message.toString())
              }
            )
          )
        }
    }
  }

  fun onClickRemoteButton() {
    val snapshot = _uiState.valueIfMatchType<ChooseSourceUiState.Choose>() ?: return

    viewModelScope.launch {
      _uiState.emit(snapshot.copy(shouldOpenInputPlayerNameModal = true))
    }
  }

  fun onClickFakeButton() {
    viewModelScope.launch {
      with(localPrefRepository) {
        setChosenApiDataSource(ChosenApiDataSource.FAKE)
        setPlayerId(PlayerId("fake-player-id"))
      }

      _uiState.emit(ChooseSourceUiState.RestartApp)
    }
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
        _uiState.emit(snapshot.copy(shouldOpenInputPlayerNameModal = false))
      }
    }
  }

}