package black.bracken.zeropoint.data.infra.repo.localcache

import black.bracken.zeropoint.data.kernel.domain.PlayerId
import black.bracken.zeropoint.data.kernel.domain.fake
import black.bracken.zeropoint.data.kernel.repo.LocalCacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object FakeLocalCacheRepository : LocalCacheRepository {

  override val playerIdFlow: Flow<PlayerId?> = flowOf(PlayerId.fake())

  override suspend fun getPlayerId(): PlayerId = PlayerId.fake()

  override suspend fun setPlayerId(playerId: PlayerId) = Unit

  override suspend fun clearPlayerId() = Unit

  override fun shouldUseRemoteDataSource(): Boolean = false

  override suspend fun setShouldUseRemoteDataSource(shouldUseRemoteDataSource: Boolean) = Unit

}