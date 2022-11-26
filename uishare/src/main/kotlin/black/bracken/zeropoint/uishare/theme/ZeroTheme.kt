package black.bracken.zeropoint.uishare.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme as Md2MaterialTheme

@Composable
fun ZeroTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) {
    ZeroDarkColorScheme
  } else {
    ZeroLightColorScheme
  }

  val typography = if (darkTheme) {
    ZeroDarkTypography
  } else {
    ZeroLightTypography
  }

  Md2MaterialTheme(
    colors = if (darkTheme) {
      LegacyMd2Theme.DarkColorPalette
    } else {
      LegacyMd2Theme.LightColorPalette
    },
    shapes = LegacyMd2Theme.Shapes,
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = typography,
      content = content,
    )
  }
}