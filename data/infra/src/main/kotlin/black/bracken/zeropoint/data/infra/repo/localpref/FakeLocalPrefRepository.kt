package black.bracken.zeropoint.data.infra.repo.localpref

import black.bracken.zeropoint.data.kernel.domain.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.domain.PlayerId
import black.bracken.zeropoint.data.kernel.domain.fake
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object FakeLocalPrefRepository : LocalPrefRepository {

  override val playerIdFlow: Flow<PlayerId?> = flowOf(PlayerId.fake())

  override suspend fun getPlayerId(): PlayerId = PlayerId.fake()

  override suspend fun setPlayerId(playerId: PlayerId) = Unit

  override suspend fun clearPlayerId() = Unit

  override fun getChosenApiDataSource(): ChosenApiDataSource = ChosenApiDataSource.FAKE

  override suspend fun setChosenApiDataSource(chosenApiDataSource: ChosenApiDataSource) = Unit


}