package black.bracken.zeropoint.feature.setup.choosesource

import black.bracken.zeropoint.data.kernel.domain.Account
import black.bracken.zeropoint.data.kernel.domain.error.ValorantApiException
import black.bracken.zeropoint.data.kernel.domain.fake
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.uishare.ext.errorMessageResource
import black.bracken.zeropoint.util.test.MainDispatcherRule
import black.bracken.zeropoint.util.test.TestFlowObserver
import black.bracken.zeropoint.util.test.UiStateScenario
import black.bracken.zeropoint.util.test.shouldFollowUiStateScenario
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test

@ExperimentalCoroutinesApi
class ChooseSourceViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val mockLocalPrefRepository = mockk<LocalPrefRepository>(relaxed = true)
  private val mockValorantApiRepository = mockk<ValorantApiRepository>(relaxed = true)

  private val viewModel = ChooseSourceViewModel(
    localPrefRepository = mockLocalPrefRepository,
    valorantApiRepository = mockValorantApiRepository,
  )

  @Test
  fun onChangeRiotId_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    val riotId = "riotId"
    viewModel.onChangeRiotId(riotId)

    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { it.copy(riotId = riotId) }
    )
  }

  @Test
  fun onChangeTagline_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    val tagline = "TAG"
    viewModel.onChangeTagline(tagline)

    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { it.copy(tagline = tagline) }
    )
  }

  @Test
  fun onConfirmPlayerName_successGetAccount_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel._uiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )

    val account = Account.fake()
    coEvery { mockValorantApiRepository.getAccount(any(), any()) } returns Result.success(account)

    viewModel.onConfirmPlayerName()
    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { it.copy(shouldOpenInputPlayerNameModal = true) }
        .then { it.copy(isLoadingOnModal = true) }
        .then {
          it.copy(
            isLoadingOnModal = false,
            errorTextOnModal = null,
          )
        }
    )
  }

  @Test
  fun onConfirmPlayerName_failureGetAccount_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel._uiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )

    val exception = ValorantApiException.Unknown()
    coEvery { mockValorantApiRepository.getAccount(any(), any()) } returns Result.failure(exception)

    viewModel.onConfirmPlayerName()
    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { it.copy(shouldOpenInputPlayerNameModal = true) }
        .then { it.copy(isLoadingOnModal = true) }
        .then {
          it.copy(
            isLoadingOnModal = false,
            errorTextOnModal = exception.errorMessageResource,
          )
        }
    )
  }

  @Test
  fun onClickRemoteButton_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel.onClickRemoteButton()
    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { it.copy(shouldOpenInputPlayerNameModal = true) }
    )
  }

  @Test
  fun onCloseBottomSheet_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel._uiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )

    viewModel.onCloseBottomSheet()
    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { it.copy(shouldOpenInputPlayerNameModal = true) }
        .then { it.copy(shouldOpenInputPlayerNameModal = false) }
    )
  }

  @Test
  fun afterCloseBottomSheet_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel._uiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )

    viewModel.afterCloseBottomSheet()
    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { it.copy(shouldOpenInputPlayerNameModal = true) }
        .then { it.copy(shouldOpenInputPlayerNameModal = false) }
    )
  }

}