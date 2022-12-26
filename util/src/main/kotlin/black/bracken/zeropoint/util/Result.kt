package black.bracken.zeropoint.util

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.SuspendableResultBinding
import com.github.michaelbull.result.coroutines.binding.binding

typealias Res<R, L> = Result<R, L>

suspend inline fun <V, E> comprehension(crossinline block: suspend SuspendableResultBinding<E>.() -> V): Result<V, E> =
  binding(block)