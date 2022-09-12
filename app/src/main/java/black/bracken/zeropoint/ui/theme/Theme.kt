package black.bracken.zeropoint.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
  primary = ValorantRed,
  onPrimary = Color.Black,

  secondary = ValorantRed,
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
  primary = ValorantRed,
  onPrimary = Color.Black,

  secondary = ValorantRed,
)

@Composable
fun ZeroPointTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content,
  )
}
