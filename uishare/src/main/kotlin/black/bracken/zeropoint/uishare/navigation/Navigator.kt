package black.bracken.zeropoint.uishare.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController

object NoArgs

interface Navigator<in P> {
  val destination: NavRoute
  val arguments: List<NamedNavArgument>

  fun navigate(navController: NavController, param: P)
}
