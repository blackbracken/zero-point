package black.bracken.zeropoint.featurecommon.composable

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

// cf. https://stackoverflow.com/a/69780826

@Composable
fun AutoResizeText(
  text: String,
  maxFontSize: TextUnit,
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  lineHeight: TextUnit = TextUnit.Unspecified,
  overflow: TextOverflow = TextOverflow.Clip,
  softWrap: Boolean = true,
  maxLines: Int = 1,
  style: TextStyle = LocalTextStyle.current,
) {
  AutoResizeText(
    text = text,
    fontSizeRange = FontSizeRange(
      max = maxFontSize,
      min = 4.sp,
    ),
    modifier = modifier,
    color = color,
    fontStyle = fontStyle,
    fontWeight = fontWeight,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
    lineHeight = lineHeight,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    style = style,
  )
}

@Composable
fun AutoResizeText(
  text: String,
  fontSizeRange: FontSizeRange,
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  lineHeight: TextUnit = TextUnit.Unspecified,
  overflow: TextOverflow = TextOverflow.Clip,
  softWrap: Boolean = true,
  maxLines: Int = 1,
  style: TextStyle = LocalTextStyle.current,
) {
  var fontSizeValue by remember { mutableStateOf(fontSizeRange.max.value) }
  var readyToDraw by remember { mutableStateOf(false) }

  Text(
    text = text,
    color = color,
    maxLines = maxLines,
    fontStyle = fontStyle,
    fontWeight = fontWeight,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
    lineHeight = lineHeight,
    overflow = overflow,
    softWrap = softWrap,
    style = style,
    fontSize = fontSizeValue.sp,
    onTextLayout = {
      if (it.didOverflowHeight && !readyToDraw) {
        val nextFontSizeValue = fontSizeRange.decrease(fontSizeValue)
        if (nextFontSizeValue <= fontSizeRange.min.value) {
          fontSizeValue = fontSizeRange.min.value
          readyToDraw = true
        } else {
          fontSizeValue = nextFontSizeValue
        }
      } else {
        readyToDraw = true
      }
    },
    modifier = modifier.drawWithContent { if (readyToDraw) drawContent() }
  )
}

data class FontSizeRange(
  val max: TextUnit,
  val min: TextUnit,
  val decrease: (Float) -> Float = if (max <= 24.sp) {
    { it - 1.sp.value }
  } else {
    // 試行回数が増えるとレンダリングに時間が掛かるので, fontSizeに応じて計算量を小さくする
    { it * 0.9f }
  },
) {
  init {
    require(min < max)
  }
}