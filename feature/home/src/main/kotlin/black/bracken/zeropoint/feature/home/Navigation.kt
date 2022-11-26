package black.bracken.zeropoint.feature.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import black.bracken.zeropoint.feature.home.timeline.TimelineScreenCoordinator
import black.bracken.zeropoint.uishare.navigation.direction.HomeDirection

fun NavGraphBuilder.homeNavigation(navController: NavController) {
  navigation(
    startDestination = HomeDirection.Timeline.destination.value,
    route = HomeDirection.Root.destination.value,
  ) {
    composable(
      route = HomeDirection.Timeline.destination.value,
    ) {
      TimelineScreenCoordinator(
        viewModel = hiltViewModel(),
      )
    }
  }
}