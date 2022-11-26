package black.bracken.zeropoint.feature.setup

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import black.bracken.zeropoint.feature.setup.choosesource.ChooseSourceScreenCoordinator
import black.bracken.zeropoint.uishare.navigation.direction.HomeDirection
import black.bracken.zeropoint.uishare.navigation.direction.SetupDirection
import black.bracken.zeropoint.uishare.navigation.navigate

fun NavGraphBuilder.setupNavigation(navController: NavController) {
  navigation(
    startDestination = SetupDirection.ChooseSource.destination.value,
    route = SetupDirection.Root.destination.value,
  ) {
    composable(
      route = SetupDirection.ChooseSource.destination.value,
    ) {
      ChooseSourceScreenCoordinator(
        viewModel = hiltViewModel(),
        navigateToHome = {
          navController.navigate(HomeDirection.Root)
        },
      )
    }
  }
}