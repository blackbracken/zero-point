package black.bracken.zeropoint.util.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun <T> MutableStateFlow<T>.emitRenewedIn(scope: CoroutineScope, renews: (T) -> T) {
  scope.launch { emitRenewed(renews) }
}

suspend fun <T> MutableStateFlow<T>.emitRenewed(renews: (T) -> T) {
  emit(renews(value))
}