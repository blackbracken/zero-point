package black.bracken.zeropoint.uishare.navigation.router

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import black.bracken.zeropoint.uishare.navigation.NavRoute
import black.bracken.zeropoint.uishare.navigation.direction.SetupDirection
import black.bracken.zeropoint.uishare.theme.ZeroColorTokens
import androidx.compose.material.BottomNavigation as Md2BottomNavigation
import androidx.compose.material.BottomNavigationItem as Md2BottomNavigationItem
import androidx.compose.material.Icon as Md2Icon
import androidx.compose.material.Text as Md2Text

private val RoutesShouldNotShow = setOf(
  SetupDirection.Root.destination,
  SetupDirection.ChooseSource.destination,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZeroRouter(
  startDestination: NavRoute,
  content: NavGraphBuilder.(NavHostController) -> Unit,
) {
  val navController = rememberNavController()

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  Scaffold(
    bottomBar = {
      if (currentDestination != null && RoutesShouldNotShow.none { it.value == currentDestination.route }) {
        ZeroBottomBar(
          navController = navController,
          currentDestination = currentDestination,
        )
      }
    }
  ) { paddingValues ->
    Box(
      modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
      NavHost(
        navController = navController,
        startDestination = startDestination.value,
      ) {
        content(navController)
      }
    }
  }
}

@Composable
private fun ZeroBottomBar(
  navController: NavHostController,
  currentDestination: NavDestination?,
) {
  val context = LocalContext.current

  Md2BottomNavigation(
    backgroundColor = ZeroColorTokens.valorantRed,
    modifier = Modifier.navigationBarsPadding(),
  ) {
    ZeroRouterItem.values().forEach { item ->
      Md2BottomNavigationItem(
        selected = currentDestination?.hierarchy
          ?.any { it.route == item.route.value }
          ?: false,
        onClick = {
          // TODO: care reselect
          navController.navigate(item.route.value) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        icon = {
          Md2Icon(
            imageVector = item.imageVector,
            contentDescription = null,
          )
        },
        label = {
          Md2Text(text = item.label.getString(context))
        }
      )
    }
  }
}

