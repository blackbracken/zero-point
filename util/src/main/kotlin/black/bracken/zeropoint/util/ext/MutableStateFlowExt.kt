@file:Suppress("UNCHECKED_CAST")

package black.bracken.zeropoint.util.ext

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<in T>.valueIfMatchType(): T? = value as? T