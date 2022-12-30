package black.bracken.zeropoint.feature.setup.choosesource

import black.bracken.zeropoint.data.kernel.model.Account
import black.bracken.zeropoint.data.kernel.model.error.ValorantApiError
import black.bracken.zeropoint.data.kernel.model.fake
import black.bracken.zeropoint.data.kernel.repo.LocalPrefRepository
import black.bracken.zeropoint.data.kernel.repo.ValorantApiRepository
import black.bracken.zeropoint.uishare.ext.errorMessageResource
import black.bracken.zeropoint.util.test.MainDispatcherRule
import black.bracken.zeropoint.util.test.TestFlowObserver
import black.bracken.zeropoint.util.test.UiStateScenario
import black.bracken.zeropoint.util.test.shouldFollowUiStateScenario
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
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
  fun createUiState_success() = runTest {
    with(ChooseSourceViewModel.Companion) {

      val uiState = MutableStateFlow(ChooseSourceUiState.Initial)

      f()
      this.f()

      this.createUiState()
    }
  }

  @Test
  fun onChangeRiotId_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    val riotId = "riotId"
    viewModel.onChangeRiotId(riotId)

    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { copy(riotId = riotId) }
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
        .then { copy(tagline = tagline) }
    )
  }

  @Test
  fun onConfirmPlayerName_successGetAccount_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel._uiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )

    val account = Account.fake()
    coEvery { mockValorantApiRepository.getAccount(any(), any()) } returns Ok(account)

    viewModel.onConfirmPlayerName()
    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { copy(shouldOpenInputPlayerNameModal = true) }
        .then { copy(isLoadingOnModal = true) }
        .then {
          copy(
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

    val error = ValorantApiRepository.Error.ApiError(ValorantApiError.fromStatusCode(404))
    coEvery { mockValorantApiRepository.getAccount(any(), any()) } returns Err(error)

    viewModel.onConfirmPlayerName()
    runCurrent()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { copy(shouldOpenInputPlayerNameModal = true) }
        .then { copy(isLoadingOnModal = true) }
        .then {
          copy(
            isLoadingOnModal = false,
            errorTextOnModal = error.error.errorMessageResource,
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
        .then { copy(shouldOpenInputPlayerNameModal = true) }
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
        .then { copy(shouldOpenInputPlayerNameModal = true) }
        .then { copy(shouldOpenInputPlayerNameModal = false) }
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
        .then { copy(shouldOpenInputPlayerNameModal = true) }
        .then { copy(shouldOpenInputPlayerNameModal = false) }
    )
  }

}