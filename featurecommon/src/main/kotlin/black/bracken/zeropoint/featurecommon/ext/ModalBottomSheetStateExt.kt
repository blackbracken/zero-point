@file:OptIn(ExperimentalMaterialApi::class)

package black.bracken.zeropoint.featurecommon.ext

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.delay
import java.util.concurrent.CancellationException

// IMEが閉じている最中に ModalBottomSheetState.hide() を呼び出すと, 実行がキャンセルされ確実に閉じない場合がある
suspend fun ModalBottomSheetState.forceHide() = forceHideRecursively(5)

private suspend fun ModalBottomSheetState.forceHideRecursively(count: Int) {
  try {
    if (count > 0) hide()
  } catch (_: CancellationException) {
    delay(1000 / 60)
    forceHideRecursively(count - 1)
  }
}