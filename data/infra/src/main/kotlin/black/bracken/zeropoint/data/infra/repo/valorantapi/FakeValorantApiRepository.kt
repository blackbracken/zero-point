package black.bracken.zeropoint.data.infra.repo.valorantapi

import black.bracken.zeropoint.data.kernel.domain.Account
import black.bracken.zeropoint.data.kernel.domain.fake
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository

object FakeValorantApiRepository : ValorantApiRepository {

  override suspend fun getAccount(riotId: String, tagline: String): Result<Account> =
    Result.success(Account.fake())

}