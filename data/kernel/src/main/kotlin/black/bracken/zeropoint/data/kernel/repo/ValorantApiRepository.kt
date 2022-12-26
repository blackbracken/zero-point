package black.bracken.zeropoint.data.kernel.repo

import black.bracken.zeropoint.data.kernel.model.Account
import black.bracken.zeropoint.data.kernel.model.PlayerId
import black.bracken.zeropoint.data.kernel.model.error.ValorantApiError
import black.bracken.zeropoint.util.Res

interface ValorantApiRepository {

  suspend fun getAccount(riotId: String, tagline: String): Res<Account, Error>

  suspend fun getAccount(playerId: PlayerId): Res<Account, Error>

  sealed interface Error {
    object SerializationError : Error
    data class ApiError(val error: ValorantApiError) : Error
  }

}
