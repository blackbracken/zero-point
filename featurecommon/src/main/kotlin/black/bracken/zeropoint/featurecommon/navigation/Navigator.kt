package black.bracken.zeropoint.featurecommon.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController

typealias NoArg = Unit

interface Navigator<in P> {
  val destinationId: String
  val arguments: List<NamedNavArgument>

  fun navigate(navController: NavController, param: P)
}
