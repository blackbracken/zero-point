package black.bracken.zeropoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import black.bracken.zeropoint.feature.setup.setupNavigation
import black.bracken.zeropoint.featurecommon.navigation.direction.SetupDirection
import black.bracken.zeropoint.ui.theme.ZeroPointTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ZeroPointTheme {
        ZeroPointNavRouter()
      }
    }
  }
}

@Composable
private fun ZeroPointNavRouter() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = SetupDirection.Root.destinationId,
  ) {
    setupNavigation(navController)
  }
}
