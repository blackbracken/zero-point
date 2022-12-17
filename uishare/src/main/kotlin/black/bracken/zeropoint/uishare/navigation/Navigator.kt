package black.bracken.zeropoint.uishare.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import kotlinx.collections.immutable.ImmutableList

object NoArgs

interface Navigator<in P> {
  val destination: NavRoute
  val arguments: ImmutableList<NamedNavArgument>

  fun navigate(navController: NavController, param: P)
}
