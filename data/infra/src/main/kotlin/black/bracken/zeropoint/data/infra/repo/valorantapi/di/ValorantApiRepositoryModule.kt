package black.bracken.zeropoint.data.infra.repo.valorantapi.di

import black.bracken.zeropoint.data.infra.repo.valorantapi.FakeValorantApiRepository
import black.bracken.zeropoint.data.infra.repo.valorantapi.RemoteValorantApiRepository
import black.bracken.zeropoint.data.infra.repo.valorantapi.UnofficialValorantApiDataSource
import black.bracken.zeropoint.data.kernel.domain.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
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
    localPrefRepository: LocalPrefRepository,
    unofficialValorantApiDataSource: UnofficialValorantApiDataSource,
  ): ValorantApiRepository {
    return if (localPrefRepository.getChosenApiDataSource() == ChosenApiDataSource.REMOTE) {
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