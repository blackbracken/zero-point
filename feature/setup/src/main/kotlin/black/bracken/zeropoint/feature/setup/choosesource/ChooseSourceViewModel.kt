package black.bracken.zeropoint.feature.setup.choosesource

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.data.kernel.domain.error.ValorantApiException
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.uishare.ext.errorMessageResource
import black.bracken.zeropoint.uishare.util.StringResource
import black.bracken.zeropoint.util.ext.emitRenewedIn
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
  val _uiState = MutableStateFlow(ChooseSourceUiState.Initial)
  val uiState get() = _uiState.asStateFlow()

  fun onChangeRiotId(riotId: String) {
    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(riotId = riotId)
    }
  }

  fun onChangeTagline(tagline: String) {
    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(
        tagline = tagline
      )
    }
  }

  fun onConfirmPlayerName() {
    viewModelScope.launch {
      val snapshot = uiState.value

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
    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(shouldOpenInputPlayerNameModal = true)
    }
  }

  fun onCloseBottomSheet() {
    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(shouldOpenInputPlayerNameModal = false)
    }
  }

  fun afterCloseBottomSheet() {
    if (uiState.value.shouldOpenInputPlayerNameModal) {
      _uiState.emitRenewedIn(viewModelScope) { uiState ->
        uiState.copy(shouldOpenInputPlayerNameModal = false)
      }
    }
  }

}