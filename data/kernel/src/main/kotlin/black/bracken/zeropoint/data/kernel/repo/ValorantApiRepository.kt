package black.bracken.zeropoint.data.kernel.repo

import black.bracken.zeropoint.data.kernel.domain.Account
import kotlinx.serialization.SerializationException

interface ValorantApiRepository {

  /**
   * @exception ValorantApiException
   * @exception SerializationException
   */
  suspend fun getAccount(riotId: String, tagline: String): Result<Account>

}