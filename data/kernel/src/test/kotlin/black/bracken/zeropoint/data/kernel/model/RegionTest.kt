package black.bracken.zeropoint.data.kernel.model

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class RegionTest {

  @Test
  fun from_returnsRegion_givenExists() {
    val actual = Region.from("ap")

    actual.shouldBe(Region.AP)
  }

  @Test
  fun from_throwsException_givenNotExists() {
    shouldThrowExactly<IllegalArgumentException> {
      Region.from("not-exists-region")
    }
  }
}
