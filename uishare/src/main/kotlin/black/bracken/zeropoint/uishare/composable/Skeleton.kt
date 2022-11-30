package black.bracken.zeropoint.uishare.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.withSaveLayer
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.core.graphics.transform
import black.bracken.zeropoint.uishare.theme.ZeroColorTokens
import kotlin.math.tan

@Composable
fun Skeleton(
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .clip(RoundedCornerShape(4.dp))
      .shimmer()
      .background(ZeroColorTokens.objectMediumEmphasis),
  ) {}
}

// cf. https://github.com/google/accompanist/pull/694
fun Modifier.shimmer(
  visible: Boolean = true,
): Modifier = composed {
  var progress: Float by remember { mutableStateOf(0f) }
  if (visible) {
    val infiniteTransition = rememberInfiniteTransition()
    progress = infiniteTransition.animateFloat(
      initialValue = 0f,
      targetValue = 1f,
      animationSpec = infiniteRepeatable(
        tween(
          durationMillis = 1250,
          delayMillis = 200,
          easing = LinearEasing
        ),
        repeatMode = RepeatMode.Restart
      ),
    ).value
  }
  ShimmerModifier(visible = visible, progress = progress)
}

internal class ShimmerModifier(
  private val visible: Boolean,
  private val progress: Float,
) : DrawModifier, LayoutModifier {
  private val cleanPaint = Paint()
  private val paint = Paint().apply {
    isAntiAlias = true
    style = PaintingStyle.Fill
    blendMode = BlendMode.SrcIn
  }
  private val angle = 20f
  private val angleTan = tan(Math.toRadians(angle.toDouble())).toFloat()
  private var translateHeight = 0f
  private var translateWidth = 0f
  private val intensity = 0.1f
  private val dropOff = 0.6f
  private val colors = listOf(
    ZeroColorTokens.objectMediumEmphasis.copy(alpha = 0.5f),
    ZeroColorTokens.objectLowEmphasis.copy(alpha = 0.9f),
    ZeroColorTokens.objectLowEmphasis.copy(alpha = 0.9f),
    ZeroColorTokens.objectMediumEmphasis.copy(alpha = 0.5f),
  )
  private val colorStops: List<Float> = listOf(
    ((1f - intensity - dropOff) / 2f).coerceIn(0f, 1f),
    ((1f - intensity - 0.001f) / 2f).coerceIn(0f, 1f),
    ((1f + intensity + 0.001f) / 2f).coerceIn(0f, 1f),
    ((1f + intensity + dropOff) / 2f).coerceIn(0f, 1f)
  )

  override fun ContentDrawScope.draw() {
    drawIntoCanvas {
      it.withSaveLayer(Rect(0f, 0f, size.width, size.height), paint = cleanPaint) {
        drawContent()
        if (visible) {
          val dx = offset(-translateWidth, translateWidth, progress)
          val dy = 0f

          paint.shader?.transform {
            reset()
            postRotate(angle, size.width / 2f, size.height / 2f)
            postTranslate(dx, dy)
          }
          it.drawRect(Rect(0f, 0f, size.width, size.height), paint = paint)
        }
      }
    }
  }

  override fun MeasureScope.measure(
    measurable: Measurable,
    constraints: Constraints
  ): MeasureResult {
    val placeable = measurable.measure(constraints)
    val size = Size(width = placeable.width.toFloat(), height = placeable.height.toFloat())
    updateSize(size)
    return layout(placeable.width, placeable.height) {
      placeable.place(0, 0)
    }
  }

  private fun updateSize(size: Size) {
    translateWidth = size.width + angleTan * size.height
    translateHeight = size.height + angleTan * size.width
    val toOffset = Offset(size.width, 0f)
    paint.shader = LinearGradientShader(
      Offset(0f, 0f),
      toOffset,
      colors,
      colorStops
    )
  }

  private fun offset(start: Float, end: Float, progress: Float): Float {
    return start + (end - start) * progress
  }
}

@Preview
@Composable
fun PreviewSkeleton() {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .height(128.dp)
      .padding(16.dp),
  ) {
    Skeleton(modifier = Modifier.size(96.dp))

    Spacer(modifier = Modifier.width(24.dp))

    Column(
      verticalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier.fillMaxHeight(),
    ) {
      Skeleton(
        modifier = Modifier
          .fillMaxWidth()
          .height(24.dp)
      )
      Skeleton(
        modifier = Modifier
          .fillMaxWidth()
          .height(24.dp)
      )
      Skeleton(
        modifier = Modifier
          .fillMaxWidth()
          .height(24.dp)
      )
    }
  }
}
