package black.bracken.zeropoint.data.infra.repo.valorantapi.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchesResponse(
  val metadata: MetadataResponse,
  val players: PlayersResponse,
)

@Serializable
data class MatchResponse(
  val map: String,
)

@Serializable
data class MetadataResponse(
  val map: String,
  val mode: String,
  @SerialName(value = "matchid")
  val matchId: String,
)

@Serializable
data class PlayersResponse(
  @SerialName("all_players")
  val allPlayers: List<PlayerResponse>
)

@Serializable
data class PlayerResponse(
  @SerialName(value = "puuid")
  val playerId: String,
  val name: String,
  val tag: String,
  val team: String,
  val level: Int,
  val character: String,
  @SerialName(value = "currenttier")
  val currentTier: Int,
)

@Serializable
data class TeamResponse(
  @SerialName(value = "has_won")
  val hasWon: Boolean,
  @SerialName(value = "rounds_won")
  val roundsWon: Int,
  @SerialName(value = "rounds_lost")
  val roundsLost: Int,
)

@Serializable
data class RoundResponse(
  @SerialName(value = "player_stats")
  val playerStats: List<PlayerStatResponse>,
)

@Serializable
data class PlayerStatResponse(
  @SerialName(value = "score")
  val score: Int,
)