package black.bracken.zeropoint.uishare.ext

import black.bracken.zeropoint.data.kernel.model.error.ValorantApiError
import black.bracken.zeropoint.uishare.util.StringResource
import black.bracken.zeropoint.resource.R as ResR

val ValorantApiError.errorMessageResource: StringResource
  get() = when (this) {
    ValorantApiError.BadRequest -> StringResource(ResR.string.error_bad_request)
    ValorantApiError.Forbidden -> StringResource(ResR.string.error_forbidden)
    ValorantApiError.NotFound -> StringResource(ResR.string.error_player_not_found)
    ValorantApiError.ServiceUnavailable -> StringResource(ResR.string.error_service_unavailable)
    ValorantApiError.Timeout -> StringResource(ResR.string.error_timeout)
    ValorantApiError.TooManyRequests -> StringResource(ResR.string.error_too_many_requests)
    is ValorantApiError.Unknown -> StringResource(
      ResR.string.error_unknown,
      this.message.toString()
    )
  }