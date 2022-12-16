package black.bracken.zeropoint.data.kernel.repo.fake

import black.bracken.zeropoint.data.kernel.model.Account
import black.bracken.zeropoint.data.kernel.model.PlayerId
import black.bracken.zeropoint.data.kernel.model.fake
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import kotlinx.coroutines.delay

object FakeValorantApiRepository : ValorantApiRepository {

  override suspend fun getAccount(riotId: String, tagline: String): Result<Account> =
    Result.success(Account.fake())

  override suspend fun getAccount(playerId: PlayerId): Result<Account> {
    delay(4000L)
    return Result.success(Account.fake())
  }
}
