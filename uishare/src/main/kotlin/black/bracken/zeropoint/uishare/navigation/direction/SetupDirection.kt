package black.bracken.zeropoint.uishare.navigation.direction

import black.bracken.zeropoint.uishare.navigation.NavRoute
import black.bracken.zeropoint.uishare.navigation.Navigator
import black.bracken.zeropoint.uishare.navigation.NoArgs
import black.bracken.zeropoint.uishare.navigation.NoArgsNavigator

sealed interface SetupDirection {

  object Root : SetupDirection,
    Navigator<NoArgs> by NoArgsNavigator(NavRoute(PREFIX, "root"))

  object ChooseSource : SetupDirection,
    Navigator<NoArgs> by NoArgsNavigator(NavRoute(PREFIX, "choosesource"))

  companion object {
    private const val PREFIX = "setup"
  }

}