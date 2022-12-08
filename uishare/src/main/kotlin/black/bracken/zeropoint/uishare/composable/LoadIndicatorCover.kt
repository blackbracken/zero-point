package black.bracken.zeropoint.uishare.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import black.bracken.zeropoint.uishare.theme.ZeroColorTokens
import black.bracken.zeropoint.resource.R as ResR

@Composable
fun LoadIndicatorCover(
  isLoading: Boolean,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = modifier.wrapContentSize(),
  ) {
    content()

    AnimatedVisibility(
      visible = isLoading,
      enter = fadeIn(),
      exit = fadeOut(),
    ) {
      LoadIndicator()
    }
  }
}

private val LoadIndicatorSize = 88.dp

@Composable
private fun LoadIndicator() {
  val alpha = rememberInfiniteTransition()
    .animateFloat(
      initialValue = 0.3f,
      targetValue = 1f,
      animationSpec = infiniteRepeatable(
        animation = tween(
          durationMillis = 1000,
          easing = FastOutSlowInEasing
        )
      )
    )

  Image(
    painter = painterResource(id = ResR.drawable.load_indicator),
    contentDescription = null,
    colorFilter = ColorFilter.tint(
      if (isSystemInDarkTheme()) ZeroColorTokens.objectMediumEmphasis else ZeroColorTokens.objectLowEmphasis
    ),
    modifier = Modifier
      .size(LoadIndicatorSize)
      .offset(
        x = 1.dp,
        y = 1.dp,
      ),
  )

  Image(
    painter = painterResource(id = ResR.drawable.load_indicator),
    contentDescription = null,
    colorFilter = ColorFilter.tint(ZeroColorTokens.valorantRed),
    modifier = Modifier
      .size(LoadIndicatorSize)
      .alpha(alpha.value),
  )
}

@Preview
@Composable
private fun PreviewLoadAnimation() {
  LoadIndicator()
}