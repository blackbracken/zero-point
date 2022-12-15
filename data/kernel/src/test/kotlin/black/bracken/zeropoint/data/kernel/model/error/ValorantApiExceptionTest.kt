package black.bracken.zeropoint.data.kernel.model.error

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.test.Test

class ValorantApiExceptionTest {

  @Test
  fun fromStatusCode_returnsNotFound_given404() {
    val actual = ValorantApiException.fromStatusCode(404)

    actual.shouldBe(ValorantApiException.NotFound)
  }

  @Test
  fun fromStatusCode_returnsUnknown_given0() {
    val actual = ValorantApiException.fromStatusCode(0)

    actual.shouldBeInstanceOf<ValorantApiException.Unknown>()
  }

}