package black.bracken.zeropoint.uishare.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ZeroColors(
  val winnerEmphasis: Color,
  val loserEmphasis: Color,
)

internal val DarkZeroColors = ZeroColors(
  winnerEmphasis = ZeroColorTokens.valorantWinner,
  loserEmphasis = ZeroColorTokens.valorantLoser,
)

internal val LightZeroColors = ZeroColors(
  winnerEmphasis = ZeroColorTokens.valorantWinner,
  loserEmphasis = ZeroColorTokens.valorantLoserEmphasisLight,
)

val LocalZeroColors = staticCompositionLocalOf<ZeroColors> {
  throw UninitializedPropertyAccessException()
}