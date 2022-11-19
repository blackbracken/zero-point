package black.bracken.zeropoint.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.MaterialTheme as M2MaterialTheme

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
  primary = ValorantRed,
  primaryVariant = ValorantRed,
  onPrimary = Color.White,
  secondary = ValorantRed,
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
  primary = ValorantRed,
  primaryVariant = ValorantRed,
  onPrimary = Color.White,
  secondary = ValorantRed,
)

private val DarkColorScheme = darkColorScheme(
  primary = ZeroColorTokens.valorantRed,
  primaryContainer = ValorantRed,
  secondary = ValorantRed,
  secondaryContainer = ValorantRed,
  tertiary = ValorantRed,
  onPrimary = Color.White,
  onSecondary = Color.White,
  onTertiary = Color.White,
  onBackground = Color.White,
  inversePrimary = Color.White,
)

private val LightColorScheme = lightColorScheme(
  primary = ZeroColorTokens.valorantRed,
  primaryContainer = ValorantRed,
  secondary = ValorantRed,
  secondaryContainer = ValorantRed,
  tertiary = ValorantRed,
  onPrimary = Color.Black,
  onSecondary = Color.Black,
  onTertiary = Color.Black,
  onBackground = Color.Black,
  surface = Color.Red,
  surfaceVariant = Color.Red,
  inversePrimary = Color.Black,
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

  val colorScheme = if (darkTheme) {
    DarkColorScheme
  } else {
    LightColorScheme
  }

  val typography = if (darkTheme) {
    DarkTypography
  } else {
    LightTypography
  }

  M2MaterialTheme(
    colors = colors,
    shapes = Shapes
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = typography,
      content = content,
    )
  }
}
