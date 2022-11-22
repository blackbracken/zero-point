package black.bracken.zeropoint.data.infra.repo.valorantapi.di

import black.bracken.zeropoint.data.infra.repo.valorantapi.FakeValorantApiRepository
import black.bracken.zeropoint.data.infra.repo.valorantapi.RemoteValorantApiRepository
import black.bracken.zeropoint.data.infra.repo.valorantapi.UnofficialValorantApiDataSource
import black.bracken.zeropoint.data.kernel.repo.LocalCacheRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValorantApiRepositoryModule {

  @Provides
  @Singleton
  fun provideValorantApiRepository(
    localCacheRepository: LocalCacheRepository,
    unofficialValorantApiDataSource: UnofficialValorantApiDataSource,
  ): ValorantApiRepository {
    return if (localCacheRepository.shouldUseRemoteDataSource()) {
      RemoteValorantApiRepository(unofficialValorantApiDataSource)
    } else {
      FakeValorantApiRepository
    }
  }

  @Provides
  @Singleton
  fun provideUnofficialValorantApiDataSource(): UnofficialValorantApiDataSource {
    return UnofficialValorantApiDataSource()
  }

}