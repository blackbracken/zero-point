package black.bracken.zeropoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import black.bracken.zeropoint.data.kernel.repo.LocalCacheRepository
import black.bracken.zeropoint.feature.setup.setupNavigation
import black.bracken.zeropoint.ui.theme.ZeroPointTheme
import black.bracken.zeropoint.uishare.navigation.direction.SetupDirection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var localCacheRepository: LocalCacheRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      ZeroPointTheme {
        Surface {
          ZeroPointNavRouter()
        }
      }
    }
  }
}

@Composable
private fun ZeroPointNavRouter() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = SetupDirection.Root.destination.value,
  ) {
    setupNavigation(navController)
  }
}
