package black.bracken.zeropoint.data.infra.repo.valorantapi

import black.bracken.zeropoint.data.infra.repo.valorantapi.response.mapToAccount
import black.bracken.zeropoint.data.kernel.model.Account
import black.bracken.zeropoint.data.kernel.model.PlayerId
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.util.Res
import com.github.michaelbull.result.mapEither

class RemoteValorantApiRepository(
  private val unofficialValorantApiDataSource: UnofficialValorantApiDataSource
) : ValorantApiRepository {

  override suspend fun getAccount(
    riotId: String,
    tagline: String
  ): Res<Account, ValorantApiRepository.Error> {
    return unofficialValorantApiDataSource
      .getAccount(
        riotId = riotId,
        tagline = tagline,
      )
      .mapEither(
        success = { it.mapToAccount() },
        failure = { it.mapToDomainError() },
      )
  }

  override suspend fun getAccount(playerId: PlayerId): Res<Account, ValorantApiRepository.Error> {
    return unofficialValorantApiDataSource
      .getAccount(playerId = playerId.value)
      .mapEither(
        success = { it.mapToAccount() },
        failure = { it.mapToDomainError() },
      )
  }

  private fun UnofficialValorantApiDataSource.Error.mapToDomainError(): ValorantApiRepository.Error =
    when (this) {
      is UnofficialValorantApiDataSource.Error.ApiError ->
        ValorantApiRepository.Error.ApiError(valorantApiError)

      is UnofficialValorantApiDataSource.Error.SerializationError ->
        ValorantApiRepository.Error.SerializationError
    }

}