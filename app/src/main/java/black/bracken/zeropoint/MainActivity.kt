package black.bracken.zeropoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import black.bracken.zeropoint.data.kernel.repo.LocalCacheRepository
import black.bracken.zeropoint.feature.setup.setupNavigation
import black.bracken.zeropoint.uishare.navigation.router.ZeroRouter
import black.bracken.zeropoint.uishare.theme.ZeroTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var localCacheRepository: LocalCacheRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      ZeroTheme {
        ZeroRouter { navController ->
          setupNavigation(navController)
        }
      }
    }
  }

}