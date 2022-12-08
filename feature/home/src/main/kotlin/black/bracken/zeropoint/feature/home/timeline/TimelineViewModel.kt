package black.bracken.zeropoint.feature.home.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
  private val valorantApiRepository: ValorantApiRepository,
  localPrefRepository: LocalPrefRepository,
) : ViewModel() {

  private val accountOrError = localPrefRepository.playerIdFlow
    .map { playerId ->
      valorantApiRepository.getAccount(
        playerId ?: return@map Result.failure(IllegalStateException())
      )
    }

  val uiState = accountOrError
    .map { accountOrError ->
      val account = accountOrError.getOrElse { return@map TimelineUiState.Error }

      TimelineUiState.Success(
        riotId = account.name,
        tagline = account.tag,
        playerIconUrl = account.cardSmallUrl,
      )
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.Lazily,
      initialValue = TimelineUiState.Loading,
    )

}