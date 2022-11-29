package black.bracken.zeropoint.feature.setup.choosesource

import black.bracken.zeropoint.uishare.util.StringResource

sealed interface ChooseSourceUiState {

  data class Choose(
    val shouldOpenInputPlayerNameModal: Boolean = false,
    val isLoadingOnModal: Boolean = false,
    val errorTextOnModal: StringResource? = null,
    val riotId: String = "",
    val tagline: String = "",
  ) : ChooseSourceUiState

  object RestartApp : ChooseSourceUiState

  companion object {
    val Initial: Choose = Choose(
      shouldOpenInputPlayerNameModal = false,
      errorTextOnModal = null,
      isLoadingOnModal = false,
    )
  }

}

fun ChooseSourceUiState.Companion.fake() = Initial

data class ChooseSourceUiAction(
  val navigateToHome: () -> Unit,
  val onChooseRemoteSource: () -> Unit,
  val onChooseFakeSource: () -> Unit,
  val onCloseBottomSheet: () -> Unit,
  val onChangeRiotId: (String) -> Unit,
  val onChangeTagline: (String) -> Unit,
  val onConfirmPlayerName: () -> Unit,
  val afterCloseBottomSheet: () -> Unit,
) {
  companion object
}

fun ChooseSourceUiAction.Companion.fake() = ChooseSourceUiAction(
  navigateToHome = {},
  onChooseRemoteSource = {},
  onChooseFakeSource = {},
  onCloseBottomSheet = {},
  onChangeRiotId = {},
  onChangeTagline = {},
  onConfirmPlayerName = {},
  afterCloseBottomSheet = {},
)
