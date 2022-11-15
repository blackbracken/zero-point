package black.bracken.zeropoint.data.valorantapi.infra

import black.bracken.zeropoint.data.model.Account
import black.bracken.zeropoint.data.model.error.ValorantApiException
import black.bracken.zeropoint.data.valorantapi.ValorantApiRepository
import black.bracken.zeropoint.data.valorantapi.infra.response.mapToAccount
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