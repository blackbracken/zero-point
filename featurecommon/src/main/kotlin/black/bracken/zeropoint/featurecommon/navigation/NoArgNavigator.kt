package black.bracken.zeropoint.featurecommon.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController

class NoArgNavigator(
  override val destinationId: String,
) : Navigator<NoArg> {

  override val arguments: List<NamedNavArgument> = emptyList()

  override fun navigate(navController: NavController, param: NoArg) {
    navController.navigate(destinationId)
  }

}
