package black.bracken.zeropoint.featurecommon.navigation.direction

import black.bracken.zeropoint.featurecommon.navigation.Navigator
import black.bracken.zeropoint.featurecommon.navigation.NoArgs
import black.bracken.zeropoint.featurecommon.navigation.NoArgsNavigator

sealed interface SetupDirection {

  object Root : SetupDirection, Navigator<NoArgs> by NoArgsNavigator("${PREFIX}_root")

  object ChooseSource : SetupDirection,
    Navigator<NoArgs> by NoArgsNavigator("${PREFIX}_choosesource")

  companion object {
    private const val PREFIX = "setup"
  }

}