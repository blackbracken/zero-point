package black.bracken.zeropoint.data.infra.repo.localpref.di

import android.content.Context
import black.bracken.zeropoint.data.infra.repo.localpref.DeviceLocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalPrefRepositoryModule {

  @Provides
  @Singleton
  fun provideLocalPrefRepository(
    @ApplicationContext context: Context,
  ): LocalPrefRepository {
    return DeviceLocalPrefRepository(context)
  }

}