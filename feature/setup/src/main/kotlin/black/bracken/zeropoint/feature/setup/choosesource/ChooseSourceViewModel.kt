package black.bracken.zeropoint.feature.setup.choosesource

import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
    viewModelScope.launch {
      _uiState.emit(
        uiState.value.copy(riotId = riotId)
      )
    }
  }

  fun onChangeTagline(tagline: String) {
    viewModelScope.launch {
      _uiState.emit(
        uiState.value.copy(tagline = tagline.toUpperCase(LocaleList.current))
      )
    }
  }

  fun onConfirmPlayerName() {
    // TODO not implemented yet
  }

  fun onClickRemoteButton() {
    viewModelScope.launch {
      _uiState.emit(
        uiState.value.copy(opensInputPlayerNameModalBottomSheet = true)
      )
    }
  }

  fun onCloseBottomSheet() {
    viewModelScope.launch {
      _uiState.emit(
        uiState.value.copy(
          opensInputPlayerNameModalBottomSheet = false
        )
      )
    }
  }

}