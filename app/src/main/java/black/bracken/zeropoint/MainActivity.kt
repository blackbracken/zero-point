package black.bracken.zeropoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import black.bracken.zeropoint.data.kernel.model.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.feature.home.homeNavigation
import black.bracken.zeropoint.feature.setup.setupNavigation
import black.bracken.zeropoint.uishare.navigation.NavRoute
import black.bracken.zeropoint.uishare.navigation.direction.HomeDirection
import black.bracken.zeropoint.uishare.navigation.direction.SetupDirection
import black.bracken.zeropoint.uishare.navigation.router.ZeroRouter
import black.bracken.zeropoint.uishare.theme.ZeroTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var localPrefRepository: LocalPrefRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    val startDestination = switchStartDestination(localPrefRepository.getChosenApiDataSource())

    setContent {
      ZeroTheme {
        ZeroRouter(startDestination) { navController ->
          setupNavigation(navController)
          homeNavigation(navController)
        }
      }
    }
  }
}

@VisibleForTesting
fun switchStartDestination(chosenApiDataSource: ChosenApiDataSource): NavRoute {
  return if (chosenApiDataSource != ChosenApiDataSource.UNSET) {
    HomeDirection.Root.destination
  } else {
    SetupDirection.Root.destination
  }
}
