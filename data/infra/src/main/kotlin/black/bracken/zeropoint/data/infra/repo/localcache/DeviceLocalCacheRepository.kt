package black.bracken.zeropoint.data.infra.repo.localcache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import black.bracken.zeropoint.data.kernel.domain.PlayerId
import black.bracken.zeropoint.data.kernel.repo.LocalCacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localCache")

class DeviceLocalCacheRepository(
  private val context: Context,
) : LocalCacheRepository {

  override val playerIdFlow: Flow<PlayerId?> = context.dataStore.data
    .map { pref -> pref[KEY_PLAYER_ID]?.let { PlayerId(it) } }

  override suspend fun getPlayerId(): PlayerId? = playerIdFlow.first()

  override suspend fun setPlayerId(playerId: PlayerId) {
    context.dataStore.edit { pref ->
      pref[KEY_PLAYER_ID] = playerId.value
    }
  }

  override suspend fun clearPlayerId() {
    context.dataStore.edit { pref ->
      pref.remove(KEY_PLAYER_ID)
    }
  }

  companion object {
    private val KEY_PLAYER_ID = stringPreferencesKey("player_id")
  }

}