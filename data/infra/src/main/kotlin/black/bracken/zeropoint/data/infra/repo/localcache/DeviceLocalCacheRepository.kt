package black.bracken.zeropoint.data.infra.repo.localcache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import black.bracken.zeropoint.data.kernel.domain.ChosenApiDataSource
import black.bracken.zeropoint.data.kernel.domain.PlayerId
import black.bracken.zeropoint.data.kernel.repo.LocalCacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localCache")

class DeviceLocalCacheRepository(
  private val context: Context,
) : LocalCacheRepository {

  private val dataFlow = context.dataStore.data

  override val playerIdFlow: Flow<PlayerId?> = dataFlow
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

  override fun getChosenApiDataSource(): ChosenApiDataSource {
    val snapshot = runBlocking { dataFlow.firstOrNull() }

    return if (snapshot == null) {
      ChosenApiDataSource.UNSET
    } else {
      val index = snapshot[KEY_CHOSEN_API_DATA_SOURCE]

      CHOSEN_API_DATA_SOURCE_INDEX_MAP
        .entries
        .firstOrNull { it.value == index }
        ?.key
        ?: ChosenApiDataSource.UNSET
    }
  }

  override suspend fun setChosenApiDataSource(chosenApiDataSource: ChosenApiDataSource) {
    context.dataStore.edit { pref ->
      pref[KEY_CHOSEN_API_DATA_SOURCE] =
        CHOSEN_API_DATA_SOURCE_INDEX_MAP.getValue(chosenApiDataSource)
    }
  }

  companion object {
    private val KEY_PLAYER_ID = stringPreferencesKey("player_id")
    private val KEY_CHOSEN_API_DATA_SOURCE = intPreferencesKey("chosen_api_data_source")

    private val CHOSEN_API_DATA_SOURCE_INDEX_MAP: Map<ChosenApiDataSource, Int> =
      ChosenApiDataSource.values().associateWith { dataSource ->
        // use `when` for exhaustiveness
        when (dataSource) {
          ChosenApiDataSource.REMOTE -> 1
          ChosenApiDataSource.FAKE -> 2
          ChosenApiDataSource.UNSET -> 0
        }
      }
  }

}