package black.bracken.zeropoint.feature.setup.choosesource

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import black.bracken.zeropoint.uishare.composable.AutoResizeText
import black.bracken.zeropoint.uishare.composable.LoadIndicatorCover
import black.bracken.zeropoint.uishare.ext.forceHide
import black.bracken.zeropoint.uishare.theme.ZeroColorTokens
import black.bracken.zeropoint.resource.R as ResR

@Composable
fun ChooseSourceScreenCoordinator(
  viewModel: ChooseSourceViewModel,
) {
  val uiState by viewModel.uiState.collectAsState()

  ChooseSourceScreen(
    uiState = uiState,
    uiAction = ChooseSourceUiAction(
      onChooseRemoteSource = viewModel::onClickRemoteButton,
      onChooseFakeSource = {}, // TODO
      onCloseBottomSheet = viewModel::onCloseBottomSheet,
      onChangeRiotId = viewModel::onChangeRiotId,
      onChangeTagline = viewModel::onChangeTagline,
      onConfirmPlayerName = viewModel::onConfirmPlayerName,
      afterCloseBottomSheet = viewModel::afterCloseBottomSheet,
    ),
  )
}

@OptIn(
  ExperimentalMaterialApi::class,
  ExperimentalComposeUiApi::class
)
@Composable
fun ChooseSourceScreen(
  uiState: ChooseSourceUiState,
  uiAction: ChooseSourceUiAction,
) {
  val context = LocalContext.current
  val keyboardController = LocalSoftwareKeyboardController.current
  val focusManager = LocalFocusManager.current

  // ModalBottomSheetState を再生成せずに [uiState.isLoadingOnModal] を取得する
  val shouldCloseModalOnTapOutside by rememberUpdatedState(!uiState.isLoadingOnModal)
  val sheetState = rememberModalBottomSheetState(
    initialValue = ModalBottomSheetValue.Hidden,
    confirmStateChange = { shouldCloseModalOnTapOutside }
  )

  LaunchedEffect(uiState.shouldOpenInputPlayerNameModal) {
    if (uiState.shouldOpenInputPlayerNameModal) {
      sheetState.show()
    } else {
      sheetState.forceHide()
    }
  }

  LaunchedEffect(sheetState.targetValue) {
    if (sheetState.targetValue == ModalBottomSheetValue.Hidden) {
      keyboardController?.hide()
      focusManager.clearFocus()

      // アニメーション中の操作による, ModalBottomSheetの内部状態とUiState内の状態間の齟齬を解消する
      uiAction.afterCloseBottomSheet()
    }
  }

  BackHandler(
    enabled = uiState.shouldOpenInputPlayerNameModal,
  ) {
    uiAction.onCloseBottomSheet()
  }

  ModalBottomSheetLayout(
    sheetState = sheetState,
    sheetContent = {
      LoadIndicatorCover(isLoading = uiState.isLoadingOnModal) {
        InputPlayerNameModalBottomSheetContent(
          riotId = uiState.riotId,
          tagline = uiState.tagline,
          isLoadingOnModal = uiState.isLoadingOnModal,
          errorText = uiState.errorTextOnModal?.getString(context),
          onChangeRiotId = uiAction.onChangeRiotId,
          onChangeTagline = uiAction.onChangeTagline,
          onConfirmPlayerName = uiAction.onConfirmPlayerName,
        )
      }
    },
    sheetShape = RoundedCornerShape(
      topStart = 4.dp,
      topEnd = 4.dp,
    ),
    modifier = Modifier
      .fillMaxSize()
      .systemBarsPadding(),
  ) {
    ChooseSourceBackground(
      modifier = Modifier.fillMaxSize(),
    ) {
      ChooseSourceContent(
        isLoadingOnModal = uiState.isLoadingOnModal,
        onChooseRemoteSource = uiAction.onChooseRemoteSource,
        onChooseFakeSource = uiAction.onChooseFakeSource,
      )
    }
  }
}

@Composable
private fun ChooseSourceBackground(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Box(
    modifier = modifier,
  ) {
    Image(
      painter = painterResource(id = ResR.drawable.kayo_lineart),
      contentDescription = null,
      colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
      modifier = Modifier
        .fillMaxWidth()
        .alpha(0.4f)
        .align(Alignment.BottomEnd)
        .absoluteOffset(x = 96.dp)
    )

    content()
  }
}

@Composable
private fun ChooseSourceContent(
  isLoadingOnModal: Boolean,
  onChooseRemoteSource: () -> Unit,
  onChooseFakeSource: () -> Unit,
) {
  Column(
    verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 32.dp),
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(modifier = Modifier.height(96.dp))

      AutoResizeText(
        text = stringResource(ResR.string.choose_source_title),
        maxFontSize = 96.sp,
        fontFamily = FontFamily(Font(ResR.font.valorant)),
      )

      Spacer(modifier = Modifier.height(8.dp))

      AutoResizeText(
        text = stringResource(ResR.string.choose_source_subtitle),
        maxFontSize = 16.sp,
        fontFamily = FontFamily(Font(ResR.font.valorant)),
        textAlign = TextAlign.End,
        modifier = Modifier
          .fillMaxWidth(fraction = 0.75f)
          .align(Alignment.End),
      )
    }

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = stringResource(ResR.string.choose_source_choose_button_description),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(16.dp))

      Box(
        modifier = Modifier
          .fillMaxWidth(fraction = 0.4f)
          .height(1.dp)
          .background(ZeroColorTokens.objectMediumEmphasis)
      )

      Spacer(modifier = Modifier.height(32.dp))

      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(160.dp),
      ) {
        Button(
          onClick = onChooseRemoteSource,
          enabled = !isLoadingOnModal,
          modifier = Modifier.fillMaxWidth(),
        ) {
          Text(
            text = stringResource(ResR.string.choose_source_choose_button_remote_label),
            maxLines = 1,
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
          onClick = onChooseFakeSource,
          enabled = !isLoadingOnModal,
          modifier = Modifier.fillMaxWidth(),
        ) {
          Text(
            text = stringResource(ResR.string.choose_source_choose_button_fake_label),
            maxLines = 1,
          )
        }

        Spacer(modifier = Modifier.height(96.dp))
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputPlayerNameModalBottomSheetContent(
  riotId: String,
  tagline: String,
  isLoadingOnModal: Boolean,
  errorText: String?,
  onChangeRiotId: (String) -> Unit,
  onChangeTagline: (String) -> Unit,
  onConfirmPlayerName: () -> Unit,
) {
  Column(
    modifier = Modifier
      .navigationBarsPadding()
      .imePadding()
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
  ) {
    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = stringResource(ResR.string.choose_source_modal_title),
      style = MaterialTheme.typography.headlineMedium,
    )

    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = stringResource(ResR.string.choose_source_modal_subtitle),
      style = MaterialTheme.typography.bodyMedium,
    )

    Spacer(modifier = Modifier.height(24.dp))

    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      OutlinedTextField(
        value = riotId,
        enabled = !isLoadingOnModal,
        label = {
          Text(
            text = stringResource(ResR.string.choose_source_modal_riot_id_label),
            color = ZeroColorTokens.objectMediumEmphasis,
            maxLines = 1,
          )
        },
        singleLine = true,
        onValueChange = onChangeRiotId,
        modifier = Modifier.weight(6f / 10)
      )

      Text(
        text = "#",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
          .wrapContentHeight()
          .padding(horizontal = 8.dp),
      )

      OutlinedTextField(
        value = tagline,
        enabled = !isLoadingOnModal,
        label = {
          Text(
            text = stringResource(ResR.string.choose_source_modal_tagline_label),
            color = ZeroColorTokens.objectMediumEmphasis,
            maxLines = 1,
          )
        },
        singleLine = true,
        onValueChange = onChangeTagline,
        modifier = Modifier.weight(4f / 10)
      )
    }

    Spacer(modifier = Modifier.height(4.dp))

    AnimatedVisibility(visible = !errorText.isNullOrBlank()) {
      Text(
        text = errorText ?: "",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(start = 8.dp)
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      onClick = onConfirmPlayerName,
      enabled = riotId.isNotEmpty() && tagline.isNotEmpty() && !isLoadingOnModal,
      modifier = Modifier.align(Alignment.End),
    ) {
      Text(
        text = stringResource(ResR.string.choose_source_modal_send_button_label),
      )
    }

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Preview
@Composable
private fun PreviewChooseSourceScreen() {
  ChooseSourceScreen(
    uiState = ChooseSourceUiState.fake(),
    uiAction = ChooseSourceUiAction.fake(),
  )
}