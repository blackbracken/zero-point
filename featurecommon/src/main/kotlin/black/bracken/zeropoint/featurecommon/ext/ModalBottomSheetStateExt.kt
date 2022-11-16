@file:OptIn(ExperimentalMaterialApi::class)

package black.bracken.zeropoint.featurecommon.ext

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.delay
import java.util.concurrent.CancellationException

// IMEが閉じている最中に ModalBottomSheetState.hide() を呼び出すと, 実行がキャンセルされ確実に閉じない場合がある
suspend fun ModalBottomSheetState.forceHide(): Boolean = forceHide(retry = 3)

private suspend fun ModalBottomSheetState.forceHide(retry: Int): Boolean =
  try {
    val shouldTryToHide = retry > 0
    if (shouldTryToHide) {
      hide()
    }

    shouldTryToHide
  } catch (_: CancellationException) {
    delay(4 * 1000 / 60)
    forceHide(retry - 1)
  }