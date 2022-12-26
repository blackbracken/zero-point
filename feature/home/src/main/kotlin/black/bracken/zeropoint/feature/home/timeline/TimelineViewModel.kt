package black.bracken.zeropoint.feature.home.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.data.kernel.model.Account
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import com.github.michaelbull.result.getOrElse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
  private val valorantApiRepository: ValorantApiRepository,
  localPrefRepository: LocalPrefRepository,
) : ViewModel() {

  private val accountOrError = localPrefRepository.playerIdFlow
    .filterNotNull()
    .map { playerId -> valorantApiRepository.getAccount(playerId) }

  val uiState = accountOrError
    .map { accountOrError ->
      val account = accountOrError.getOrElse { error -> return@map TimelineUiState.Error }

      createUiState(account)
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.Lazily,
      initialValue = TimelineUiState.Loading,
    )

  private fun createUiState(account: Account): TimelineUiState {
    return TimelineUiState.Success(
      riotId = account.name,
      tagline = account.tag,
      playerIconUrl = account.cardSmallUrl,
    )
  }

}