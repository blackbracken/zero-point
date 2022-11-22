package black.bracken.zeropoint.data.kernel.domain

@JvmInline
value class PlayerId(val value: String) {
  companion object
}

fun PlayerId.Companion.fake(): PlayerId = PlayerId("FakePlayerId")
