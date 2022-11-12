package black.bracken.zeropoint.feature.setup.choosesource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChooseSourceUiState(
  val opensInputPlayerNameModalBottomSheet: Boolean = false,
) {
  companion object {
    val Initial = ChooseSourceUiState(
      opensInputPlayerNameModalBottomSheet = false,
    )
  }
}

class ChooseSourceViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(ChooseSourceUiState.Initial)
  val uiState get() = _uiState.asStateFlow()

  fun onClickRemoteButton() {
    viewModelScope.launch {
      _uiState.emit(
        uiState.value.copy(
          opensInputPlayerNameModalBottomSheet = true
        )
      )
    }
  }

}