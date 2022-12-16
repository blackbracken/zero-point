package black.bracken.zeropoint.data.kernel.model.error

sealed class ValorantApiException(
  override val message: String? = null
) : Exception() {

  object BadRequest : ValorantApiException()
  object Forbidden : ValorantApiException()
  object NotFound : ValorantApiException()
  object Timeout : ValorantApiException()
  object TooManyRequests : ValorantApiException()
  object ServiceUnavailable : ValorantApiException()

  data class Unknown(override val message: String? = null) : ValorantApiException()

  companion object {
    fun fromStatusCode(code: Int): ValorantApiException =
      when (code) {
        400 -> BadRequest
        403 -> Forbidden
        404 -> NotFound
        408 -> Timeout
        429 -> TooManyRequests
        503 -> ServiceUnavailable
        else -> Unknown()
      }
  }
}
