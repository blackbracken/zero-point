package black.bracken.zeropoint.data.kernel.repo

import black.bracken.zeropoint.data.kernel.model.Account
import black.bracken.zeropoint.data.kernel.model.PlayerId
import kotlinx.serialization.SerializationException

interface ValorantApiRepository {

  /**
   * @exception ValorantApiException
   * @exception SerializationException
   */
  suspend fun getAccount(riotId: String, tagline: String): Result<Account>

  /**
   * @exception ValorantApiException
   * @exception SerializationException
   */
  suspend fun getAccount(playerId: PlayerId): Result<Account>

}