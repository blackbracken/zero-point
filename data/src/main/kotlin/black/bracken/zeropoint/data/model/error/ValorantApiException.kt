package black.bracken.zeropoint.data.model.error

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

  companion object

}

internal fun ValorantApiException.Companion.fromStatusCode(code: Int): ValorantApiException =
  when (code) {
    400 -> ValorantApiException.BadRequest
    403 -> ValorantApiException.Forbidden
    404 -> ValorantApiException.NotFound
    408 -> ValorantApiException.Timeout
    429 -> ValorantApiException.TooManyRequests
    503 -> ValorantApiException.ServiceUnavailable
    else -> ValorantApiException.Unknown()
  }