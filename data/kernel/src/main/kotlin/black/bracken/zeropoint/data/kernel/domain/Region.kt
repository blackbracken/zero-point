package black.bracken.zeropoint.data.kernel.domain

enum class Region {
  AP,
  NA,
  LATAM,
  BR,
  KR,
  ;

  companion object {
    fun from(raw: String): Region = Region.valueOf(raw.uppercase())
  }
}