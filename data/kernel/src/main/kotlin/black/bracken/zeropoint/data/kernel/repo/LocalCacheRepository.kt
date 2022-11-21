package black.bracken.zeropoint.data.kernel.repo

import black.bracken.zeropoint.data.kernel.domain.PlayerId
import kotlinx.coroutines.flow.Flow

interface LocalCacheRepository {

  val playerIdFlow: Flow<PlayerId?>

  suspend fun getPlayerId(): PlayerId?

  suspend fun setPlayerId(playerId: PlayerId)

  suspend fun clearPlayerId()

}