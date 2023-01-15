package black.bracken.zeropoint.util

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

class TxMutex {
  val lockState = MutableStateFlow(false)
  private val isLocked get() = lockState.value

  private val mutex = Mutex()

  suspend fun <T> tryTransact(action: suspend () -> T): Res<T, Unit> {
    return try {
      if (mutex.tryLock()) {
        lockState.emit(true)

        Ok(action())
      } else {
        Err(Unit)
      }
    } catch (_: Throwable) {
      Err(Unit)
    } finally {
      if (mutex.isLocked) {
        mutex.unlock()

        if (isLocked) {
          lockState.emit(false)
        }
      }
    }
  }
}

fun TxMutex.withLockOn(scope: CoroutineScope, action: suspend () -> Unit) {
  scope.launch {
    tryTransact(action)
  }
}
