package black.bracken.zeropoint.data.valorantapi

import black.bracken.zeropoint.data.model.Account

interface ValorantApiRepository {

  suspend fun getAccount(riotId: String, tagline: String): Result<Account>

}