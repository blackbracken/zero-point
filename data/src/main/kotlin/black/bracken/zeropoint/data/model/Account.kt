package black.bracken.zeropoint.data.model

data class Account(
  val playerId: PlayerId,
  val accountLevel: Int,
  val region: Region,
  val name: String,
  val tag: String,
  val cardSmallUrl: String,
  val cardLargeUrl: String,
  val cardWideUrl: String,
)
