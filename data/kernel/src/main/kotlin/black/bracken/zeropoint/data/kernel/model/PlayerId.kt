package black.bracken.zeropoint.data.kernel.model

@JvmInline
value class PlayerId(val value: String) {
  companion object
}

fun PlayerId.Companion.fake(): PlayerId = PlayerId("FakePlayerId")
