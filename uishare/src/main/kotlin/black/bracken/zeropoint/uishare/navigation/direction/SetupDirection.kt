package black.bracken.zeropoint.uishare.navigation.direction

import black.bracken.zeropoint.uishare.navigation.Navigator
import black.bracken.zeropoint.uishare.navigation.NoArgs
import black.bracken.zeropoint.uishare.navigation.NoArgsNavigator

sealed interface SetupDirection {

  object Root : SetupDirection, Navigator<NoArgs> by NoArgsNavigator("${PREFIX}_root")

  object ChooseSource : SetupDirection,
    Navigator<NoArgs> by NoArgsNavigator("${PREFIX}_choosesource")

  companion object {
    private const val PREFIX = "setup"
  }

}