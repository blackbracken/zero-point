package black.bracken.zeropoint.data.kernel.repository

import black.bracken.zeropoint.data.kernel.domain.Account

interface ValorantApiRepository {

  suspend fun getAccount(riotId: String, tagline: String): Result<Account>

}