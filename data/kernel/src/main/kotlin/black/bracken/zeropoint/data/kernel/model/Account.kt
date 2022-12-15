package black.bracken.zeropoint.data.kernel.model

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
  cardSmallUrl = "https://media.valorant-api.com/playercards/38defae8-4b79-f5cc-09c1-ceb5e109c4c9/smallart.png",
  cardLargeUrl = "https://images.dog.ceo/breeds/shiba/shiba-12.jpg",
  cardWideUrl = "https://images.dog.ceo/breeds/shiba/shiba-12.jpg"
)
