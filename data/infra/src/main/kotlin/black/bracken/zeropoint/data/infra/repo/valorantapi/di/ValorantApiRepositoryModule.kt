package black.bracken.zeropoint.data.infra.repo.valorantapi.di

import black.bracken.zeropoint.data.infra.repo.valorantapi.RemoteValorantApiRepository
import black.bracken.zeropoint.data.infra.repo.valorantapi.UnofficialValorantApiDataSource
import black.bracken.zeropoint.data.kernel.model.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.data.kernel.repo.fake.FakeValorantApiRepository
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
    return when (localPrefRepository.getChosenApiDataSource()) {
      ChosenApiDataSource.UNSET, ChosenApiDataSource.REMOTE -> {
        RemoteValorantApiRepository(unofficialValorantApiDataSource)
      }

      ChosenApiDataSource.FAKE -> {
        FakeValorantApiRepository
      }
    }
  }

  @Provides
  @Singleton
  fun provideUnofficialValorantApiDataSource(): UnofficialValorantApiDataSource {
    return UnofficialValorantApiDataSource()
  }

}