package black.bracken.zeropoint.data.kernel.repo

import black.bracken.zeropoint.data.kernel.model.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.model.PlayerId
import kotlinx.coroutines.flow.Flow

interface LocalPrefRepository {

  val playerIdFlow: Flow<PlayerId?>

  suspend fun getPlayerId(): PlayerId?

  suspend fun setPlayerId(playerId: PlayerId)

  suspend fun clearPlayerId()

  fun getChosenApiDataSource(): ChosenApiDataSource

  suspend fun setChosenApiDataSource(chosenApiDataSource: ChosenApiDataSource)
}
