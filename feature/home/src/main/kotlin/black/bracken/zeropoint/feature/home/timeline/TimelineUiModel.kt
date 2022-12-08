package black.bracken.zeropoint.feature.home.timeline

sealed interface TimelineUiState {

  object Loading : TimelineUiState {
    override val showsLoadingIndicator = true
  }

  data class Success(
    val riotId: String,
    val tagline: String,
    val playerIconUrl: String,

    ) : TimelineUiState {
    override val showsLoadingIndicator = false
  }

  object Error : TimelineUiState {
    override val showsLoadingIndicator = true
  }

  val showsLoadingIndicator: Boolean

}