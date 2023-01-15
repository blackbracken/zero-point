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
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.yield
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

    advanceUntilIdle()

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

    advanceUntilIdle()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { copy(tagline = tagline) }
    )
  }

  @Test
  fun onConfirmPlayerName_successGetAccount_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel.rawUiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )
    advanceUntilIdle()

    val account = Account.fake()
    coEvery { mockValorantApiRepository.getAccount(any(), any()) } coAnswers {
      yield()
      Ok(account)
    }

    viewModel.onConfirmPlayerName()
    advanceUntilIdle()

    uiStateObserver.cancelAndCollectAll()
      .shouldFollowUiStateScenario(
        UiStateScenario
          .beginsWith(ChooseSourceUiState.Initial)
          .then { copy(shouldOpenInputPlayerNameModal = true) }
          .then { copy(inTransaction = true) }
          .then {
            copy(
              inTransaction = false,
              errorTextOnModal = null,
            )
          }
      )
  }

  @Test
  fun onConfirmPlayerName_failureGetAccount_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel.rawUiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )
    advanceUntilIdle()

    val error = ValorantApiRepository.Error.ApiError(ValorantApiError.fromStatusCode(404))
    coEvery { mockValorantApiRepository.getAccount(any(), any()) } coAnswers {
      yield()
      Err(error)
    }

    viewModel.onConfirmPlayerName()
    advanceUntilIdle()

    uiStateObserver.cancelAndCollectAll()
      .shouldFollowUiStateScenario(
        UiStateScenario
          .beginsWith(ChooseSourceUiState.Initial)
          .then { copy(shouldOpenInputPlayerNameModal = true) }
          .then { copy(inTransaction = true) }
          .then {
            copy(
              inTransaction = false,
              errorTextOnModal = error.error.errorMessageResource,
            )
          }
      )
  }

  @Test
  fun onClickRemoteButton_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel.onClickRemoteButton()
    advanceUntilIdle()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { copy(shouldOpenInputPlayerNameModal = true) }
    )
  }

  @Test
  fun onCloseBottomSheet_success_changeUiState() = runTest {
    val uiStateObserver = TestFlowObserver(this, testScheduler, viewModel.uiState).start()

    viewModel.rawUiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )
    advanceUntilIdle()

    viewModel.onCloseBottomSheet()
    advanceUntilIdle()

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

    viewModel.rawUiState.emit(
      ChooseSourceUiState.Initial.copy(shouldOpenInputPlayerNameModal = true)
    )
    advanceUntilIdle()

    viewModel.afterCloseBottomSheet()
    advanceUntilIdle()

    uiStateObserver.cancelAndCollectAll().shouldFollowUiStateScenario(
      UiStateScenario
        .beginsWith(ChooseSourceUiState.Initial)
        .then { copy(shouldOpenInputPlayerNameModal = true) }
        .then { copy(shouldOpenInputPlayerNameModal = false) }
    )
  }

}