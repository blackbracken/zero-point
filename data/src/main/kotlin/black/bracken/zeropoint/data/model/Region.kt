package black.bracken.zeropoint.data.model

import io.ktor.util.toUpperCasePreservingASCIIRules

enum class Region {
  AP,
  NA,
  LATAM,
  BR,
  KR,
  ;

  companion object {
    fun from(raw: String): Region = Region.valueOf(raw.toUpperCasePreservingASCIIRules())
  }
}