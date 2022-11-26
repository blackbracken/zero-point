package black.bracken.zeropoint.uishare.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

internal object LegacyMd2Theme {

  val Shapes: Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
  )

  @SuppressLint("ConflictingOnColor")
  val DarkColorPalette = darkColors(
    primary = ZeroColorTokens.valorantRed,
    primaryVariant = ZeroColorTokens.valorantRed,
    onPrimary = Color.White,
    secondary = ZeroColorTokens.valorantRed,
  )

  @SuppressLint("ConflictingOnColor")
  val LightColorPalette = lightColors(
    primary = ZeroColorTokens.valorantRed,
    primaryVariant = ZeroColorTokens.valorantRed,
    onPrimary = Color.White,
    secondary = ZeroColorTokens.valorantRed,
  )

}