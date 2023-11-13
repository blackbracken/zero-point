package black.bracken.zeropoint.data.kernel.model

enum class Region {
  AP,
  NA,
  LATAM,
  BR,
  KR,
  ;

  val raw: String get() = name.lowercase()

  companion object {
    fun from(raw: String): Region = Region.valueOf(raw.uppercase())
  }
}
