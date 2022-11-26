package black.bracken.zeropoint.uishare.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color

// use kotlin fill class plugin + replace params using regex like:
//   from: ([ ]+)([^ ]+) =,
//   to  : $1$2 = default.$2.copy(color = Color.White),

internal val ZeroLightTypography = Typography().let { default ->
  default.copy(
    displayLarge = default.displayLarge.copy(color = Color.Black),
    displayMedium = default.displayMedium.copy(color = Color.Black),
    displaySmall = default.displaySmall.copy(color = Color.Black),
    headlineLarge = default.headlineLarge.copy(color = Color.Black),
    headlineMedium = default.headlineMedium.copy(color = Color.Black),
    headlineSmall = default.headlineSmall.copy(color = Color.Black),
    titleLarge = default.titleLarge.copy(color = Color.Black),
    titleMedium = default.titleMedium.copy(color = Color.Black),
    titleSmall = default.titleSmall.copy(color = Color.Black),
    bodyLarge = default.bodyLarge.copy(color = Color.Black),
    bodyMedium = default.bodyMedium.copy(color = Color.Black),
    bodySmall = default.bodySmall.copy(color = Color.Black),
    labelLarge = default.labelLarge.copy(color = Color.White),
    labelMedium = default.labelMedium.copy(color = Color.White),
    labelSmall = default.labelSmall.copy(color = Color.White),
  )
}

internal val ZeroDarkTypography = Typography().let { default ->
  default.copy(
    displayLarge = default.displayLarge.copy(color = Color.White),
    displayMedium = default.displayMedium.copy(color = Color.White),
    displaySmall = default.displaySmall.copy(color = Color.White),
    headlineLarge = default.headlineLarge.copy(color = Color.White),
    headlineMedium = default.headlineMedium.copy(color = Color.White),
    headlineSmall = default.headlineSmall.copy(color = Color.White),
    titleLarge = default.titleLarge.copy(color = Color.White),
    titleMedium = default.titleMedium.copy(color = Color.White),
    titleSmall = default.titleSmall.copy(color = Color.White),
    bodyLarge = default.bodyLarge.copy(color = Color.White),
    bodyMedium = default.bodyMedium.copy(color = Color.White),
    bodySmall = default.bodySmall.copy(color = Color.White),
    labelLarge = default.labelLarge.copy(color = Color.White),
    labelMedium = default.labelMedium.copy(color = Color.White),
    labelSmall = default.labelSmall.copy(color = Color.White),
  )
}