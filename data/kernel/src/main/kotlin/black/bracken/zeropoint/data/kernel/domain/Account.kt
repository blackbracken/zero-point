package black.bracken.zeropoint.data.kernel.domain

data class Account(
  val playerId: PlayerId,
  val accountLevel: Int,
  val region: Region,
  val name: String,
  val tag: String,
  val cardSmallUrl: String,
  val cardLargeUrl: String,
  val cardWideUrl: String,
) {
  companion object
}

fun Account.Companion.fake(): Account = Account(
  playerId = PlayerId.fake(),
  accountLevel = 123,
  region = Region.AP,
  name = "MrGhost",
  tag = "DUMMY",
  cardSmallUrl = "https://images.dog.ceo/breeds/shiba/shiba-12.jpg",
  cardLargeUrl = "https://images.dog.ceo/breeds/shiba/shiba-12.jpg",
  cardWideUrl = "https://images.dog.ceo/breeds/shiba/shiba-12.jpg"
)
