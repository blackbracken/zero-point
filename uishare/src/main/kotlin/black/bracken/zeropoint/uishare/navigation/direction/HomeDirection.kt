package black.bracken.zeropoint.uishare.navigation.direction

import androidx.navigation.NavController
import black.bracken.zeropoint.uishare.navigation.NavRoute
import black.bracken.zeropoint.uishare.navigation.Navigator
import black.bracken.zeropoint.uishare.navigation.NoArgs
import black.bracken.zeropoint.uishare.navigation.NoArgsNavigator

sealed interface HomeDirection {

  object Root : HomeDirection,
    Navigator<NoArgs> by NoArgsNavigator(NavRoute(PREFIX, "root")) {

    override fun navigate(navController: NavController, param: NoArgs) {
      navController.navigate(Timeline.destination.value) {
        popUpTo(SetupDirection.Root.destination.value) {
          inclusive = true
        }
      }
    }

  }

  object Timeline : HomeDirection,
    Navigator<NoArgs> by NoArgsNavigator(NavRoute(PREFIX, "timeline"))

  companion object {
    private const val PREFIX = "home"
  }

}