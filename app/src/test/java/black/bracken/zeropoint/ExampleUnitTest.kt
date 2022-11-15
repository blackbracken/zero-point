package black.bracken.zeropoint

import black.bracken.zeropoint.data.valorantapi.infra.RemoteValorantApiRepository
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    (2 + 2).shouldBe(4)

    // TODO remove
    runBlocking {
      println(RemoteValorantApiRepository().getAccount("deadbeef", "glhf"))
    }
  }
}
