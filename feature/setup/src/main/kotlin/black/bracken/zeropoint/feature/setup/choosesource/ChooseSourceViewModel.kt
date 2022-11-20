package black.bracken.zeropoint.feature.setup.choosesource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.util.extension.emitRenewedIn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChooseSourceUiState(
  val shouldOpenInputPlayerNameModal: Boolean = false,
  val isLoadingOnModal: Boolean = false,
  val riotId: String = "",
  val tagline: String = "",
) {
  companion object {
    val Initial = ChooseSourceUiState(
      shouldOpenInputPlayerNameModal = false,
      isLoadingOnModal = false,
    )
  }
}

data class ChooseSourceUiAction(
  val onChooseRemoteSource: () -> Unit,
  val onChooseFakeSource: () -> Unit,
  val onCloseBottomSheet: () -> Unit,
  val onChangeRiotId: (String) -> Unit,
  val onChangeTagline: (String) -> Unit,
  val onConfirmPlayerName: () -> Unit,
  val afterCloseBottomSheet: () -> Unit,
)

class ChooseSourceViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(ChooseSourceUiState.Initial)
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
    val old = uiState.value

    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(
        isLoadingOnModal = true,
      )
    }

    // TODO: delete
    viewModelScope.launch {
      delay(3000L)
      _uiState.emit(old)
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