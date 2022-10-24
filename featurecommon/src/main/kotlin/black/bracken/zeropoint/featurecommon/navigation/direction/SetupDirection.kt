package black.bracken.zeropoint.featurecommon.navigation.direction

import black.bracken.zeropoint.featurecommon.navigation.Navigator
import black.bracken.zeropoint.featurecommon.navigation.NoArg
import black.bracken.zeropoint.featurecommon.navigation.NoArgNavigator

sealed interface SetupDirection {

  object Root : SetupDirection, Navigator<NoArg> by NoArgNavigator("${PREFIX}_root")

  object ChooseSource : SetupDirection, Navigator<NoArg> by NoArgNavigator("${PREFIX}_choosesource")

  companion object {
    private const val PREFIX = "setup"
  }

}