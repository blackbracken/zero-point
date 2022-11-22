package black.bracken.zeropoint.data.infra.repo.valorantapi

import black.bracken.zeropoint.data.infra.repo.valorantapi.response.mapToAccount
import black.bracken.zeropoint.data.kernel.domain.Account
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import kotlinx.serialization.SerializationException

class RemoteValorantApiRepository(
  private val unofficialValorantApiDataSource: UnofficialValorantApiDataSource = UnofficialValorantApiDataSource() // TODO: remove default param
) : ValorantApiRepository {

  /**
   * @exception ValorantApiException
   * @exception SerializationException
   */
  override suspend fun getAccount(riotId: String, tagline: String): Result<Account> =
    kotlin.runCatching {
      unofficialValorantApiDataSource
        .getAccount(
          riotId = riotId,
          tagline = tagline,
        )
        .mapToAccount()
    }

}