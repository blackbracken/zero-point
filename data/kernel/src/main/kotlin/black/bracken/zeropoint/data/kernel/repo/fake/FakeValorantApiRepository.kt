package black.bracken.zeropoint.data.kernel.repo.fake

import black.bracken.zeropoint.data.kernel.model.Account
import black.bracken.zeropoint.data.kernel.model.Match
import black.bracken.zeropoint.data.kernel.model.PlayerId
import black.bracken.zeropoint.data.kernel.model.fake
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.util.Res
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.delay

object FakeValorantApiRepository : ValorantApiRepository {

  override suspend fun getAccount(
    riotId: String,
    tagline: String
  ): Res<Account, ValorantApiRepository.Error> = Ok(Account.fake())

  override suspend fun getAccount(playerId: PlayerId): Res<Account, ValorantApiRepository.Error> {
    delay(4000L)
    return Ok(Account.fake())
  }

  override suspend fun getMatches(): Res<List<Match>, ValorantApiRepository.Error> {
    TODO()
  }
}
