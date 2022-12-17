package black.bracken.zeropoint.uishare.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

class NoArgsNavigator(
  override val destination: NavRoute,
) : Navigator<NoArgs> {

  override val arguments: ImmutableList<NamedNavArgument> = persistentListOf()

  override fun navigate(navController: NavController, param: NoArgs) {
    navController.navigate(destination.value)
  }

}
