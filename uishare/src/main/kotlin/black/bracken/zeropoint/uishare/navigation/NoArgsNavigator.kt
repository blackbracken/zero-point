package black.bracken.zeropoint.uishare.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController

class NoArgsNavigator(
  override val destinationId: String,
) : Navigator<NoArgs> {

  override val arguments: List<NamedNavArgument> = emptyList()

  override fun navigate(navController: NavController, param: NoArgs) {
    navController.navigate(destinationId)
  }

}
