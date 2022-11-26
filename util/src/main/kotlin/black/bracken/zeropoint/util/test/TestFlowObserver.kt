package black.bracken.zeropoint.util.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
class TestFlowObserver<T>(
  private val scope: CoroutineScope,
  private val scheduler: TestCoroutineScheduler,
  private val flow: Flow<T>
) {

  private val values = mutableListOf<T>()
  private lateinit var job: Job

  fun start(): TestFlowObserver<T> {
    job = scope.launch(UnconfinedTestDispatcher(scheduler)) {
      flow.toList(values)
    }

    return this
  }

  fun cancelAndCollectAll(): List<T> {
    job.cancel()

    return values
  }

}
