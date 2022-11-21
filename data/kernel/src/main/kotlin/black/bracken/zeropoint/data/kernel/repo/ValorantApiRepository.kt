package black.bracken.zeropoint.data.kernel.repo

import black.bracken.zeropoint.data.kernel.domain.Account

interface ValorantApiRepository {

  suspend fun getAccount(riotId: String, tagline: String): Result<Account>

}