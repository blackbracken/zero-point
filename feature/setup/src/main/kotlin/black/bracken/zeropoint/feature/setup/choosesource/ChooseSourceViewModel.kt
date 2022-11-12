package black.bracken.zeropoint.feature.setup.choosesource

import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.util.extension.emitRenewedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChooseSourceUiState(
  val opensInputPlayerNameModalBottomSheet: Boolean = false,
  val riotId: String = "",
  val tagline: String = "",
) {
  companion object {
    val Initial = ChooseSourceUiState(
      opensInputPlayerNameModalBottomSheet = false,
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
        tagline = tagline.toUpperCase(LocaleList.current)
      )
    }
  }

  fun onConfirmPlayerName() {
    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(
        tagline = uiState.tagline,
      )
    }
    // TODO not implemented yet
  }

  fun onClickRemoteButton() {
    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(opensInputPlayerNameModalBottomSheet = true)
    }
  }

  fun onCloseBottomSheet() {
    _uiState.emitRenewedIn(viewModelScope) { uiState ->
      uiState.copy(opensInputPlayerNameModalBottomSheet = false)
    }
  }

}