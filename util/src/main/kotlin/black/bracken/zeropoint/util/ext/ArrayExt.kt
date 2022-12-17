package black.bracken.zeropoint.util.ext

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun <T> Array<T>.toImmutableList(): ImmutableList<T> = toList().toImmutableList()
