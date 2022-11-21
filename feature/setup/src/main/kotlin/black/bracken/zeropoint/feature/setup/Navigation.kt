package black.bracken.zeropoint.feature.setup

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import black.bracken.zeropoint.feature.setup.choosesource.ChooseSourceScreenCoordinator
import black.bracken.zeropoint.uishare.navigation.direction.SetupDirection

fun NavGraphBuilder.setupNavigation(navController: NavController) {
  navigation(
    startDestination = SetupDirection.ChooseSource.destinationId,
    route = SetupDirection.Root.destinationId,
  ) {
    composable(
      route = SetupDirection.ChooseSource.destinationId,
    ) {
      ChooseSourceScreenCoordinator(
        viewModel = hiltViewModel(),
      )
    }
  }
}