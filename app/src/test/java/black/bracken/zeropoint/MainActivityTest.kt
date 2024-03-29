package black.bracken.zeropoint

import black.bracken.zeropoint.data.infra.repo.valorantapi.UnofficialValorantApiDataSource
import black.bracken.zeropoint.data.kernel.model.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.model.Region
import black.bracken.zeropoint.uishare.navigation.direction.HomeDirection
import black.bracken.zeropoint.uishare.navigation.direction.SetupDirection
import black.bracken.zeropoint.util.test.MainDispatcherRule
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainActivityTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  @Test
  fun switchStartDestination() = runTest {
    switchStartDestination(ChosenApiDataSource.UNSET).shouldBe(SetupDirection.Root.destination)
    switchStartDestination(ChosenApiDataSource.REMOTE).shouldBe(HomeDirection.Root.destination)
    switchStartDestination(ChosenApiDataSource.FAKE).shouldBe(HomeDirection.Root.destination)
  }

  @Test
  fun f() = runTest {
    val x = UnofficialValorantApiDataSource().getMatches("deadbeef", "glhf", Region.AP)

    println("blackbracken $x")
  }
}
