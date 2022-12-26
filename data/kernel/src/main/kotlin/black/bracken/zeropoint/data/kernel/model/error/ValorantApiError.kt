package black.bracken.zeropoint.data.kernel.model.error

sealed class ValorantApiError(
  override val message: String? = null
) : Exception() {

  object BadRequest : ValorantApiError()
  object Forbidden : ValorantApiError()
  object NotFound : ValorantApiError()
  object Timeout : ValorantApiError()
  object TooManyRequests : ValorantApiError()
  object ServiceUnavailable : ValorantApiError()

  data class Unknown(override val message: String? = null) : ValorantApiError()

  companion object {
    fun fromStatusCode(code: Int): ValorantApiError =
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
