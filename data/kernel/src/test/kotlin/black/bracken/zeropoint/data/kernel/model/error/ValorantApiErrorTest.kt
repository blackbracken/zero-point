package black.bracken.zeropoint.data.kernel.model.error

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.test.Test

class ValorantApiErrorTest {

  @Test
  fun fromStatusCode_returnsNotFound_given404() {
    val actual = ValorantApiError.fromStatusCode(404)

    actual.shouldBe(ValorantApiError.NotFound)
  }

  @Test
  fun fromStatusCode_returnsUnknown_given0() {
    val actual = ValorantApiError.fromStatusCode(0)

    actual.shouldBeInstanceOf<ValorantApiError.Unknown>()
  }
}
