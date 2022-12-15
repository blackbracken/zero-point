package black.bracken.zeropoint.uishare.ext

import black.bracken.zeropoint.data.kernel.model.error.ValorantApiException
import black.bracken.zeropoint.uishare.util.StringResource
import black.bracken.zeropoint.resource.R as ResR

val ValorantApiException.errorMessageResource: StringResource
  get() = when (this) {
    ValorantApiException.BadRequest -> StringResource(ResR.string.error_bad_request)
    ValorantApiException.Forbidden -> StringResource(ResR.string.error_forbidden)
    ValorantApiException.NotFound -> StringResource(ResR.string.error_player_not_found)
    ValorantApiException.ServiceUnavailable -> StringResource(ResR.string.error_service_unavailable)
    ValorantApiException.Timeout -> StringResource(ResR.string.error_timeout)
    ValorantApiException.TooManyRequests -> StringResource(ResR.string.error_too_many_requests)
    is ValorantApiException.Unknown -> StringResource(
      ResR.string.error_unknown,
      this.message.toString()
    )
  }