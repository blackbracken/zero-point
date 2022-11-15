package black.bracken.zeropoint.data.valorantapi.infra.response

import black.bracken.zeropoint.data.model.Account
import black.bracken.zeropoint.data.model.PlayerId
import black.bracken.zeropoint.data.model.Region
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
  @SerialName(value = "puuid")
  val playerId: String,
  val region: String,
  @SerialName(value = "account_level")
  val accountLevel: Int,
  val name: String,
  val tag: String,
  val card: Card,
)

internal fun AccountResponse.mapToAccount(): Account = Account(
  playerId = PlayerId(playerId),
  accountLevel = accountLevel,
  region = Region.from(region),
  name = name,
  tag = tag,
  cardSmallUrl = card.smallUrl,
  cardLargeUrl = card.largeUrl,
  cardWideUrl = card.wideUrl,
)

@Serializable
data class Card(
  @SerialName(value = "small")
  val smallUrl: String,
  @SerialName(value = "large")
  val largeUrl: String,
  @SerialName(value = "wide")
  val wideUrl: String,
)