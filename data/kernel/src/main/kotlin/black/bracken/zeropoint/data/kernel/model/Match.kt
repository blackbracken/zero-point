package black.bracken.zeropoint.data.kernel.model

data class Match(
  val agent: String, // TODO: use domain instead of text
  val rank: Int, // TODO: use domain
  val kills: Int,
  val deaths: Int,
  val assists: Int,
  val order: Int,
  val score: Int,
  val map: String,
  val mode: String,
  val result: Int, // TODO: use sealed class
)
