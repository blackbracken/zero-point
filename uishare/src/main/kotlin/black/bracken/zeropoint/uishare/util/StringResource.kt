package black.bracken.zeropoint.uishare.util

import android.content.Context
import androidx.annotation.StringRes
import black.bracken.zeropoint.util.ext.toImmutableList
import kotlinx.collections.immutable.ImmutableList

data class StringResource(
  @StringRes val id: Int,
  val params: ImmutableList<Any>,
) {

  constructor(@StringRes id: Int, vararg params: Any) : this(id, params.toImmutableList())

  fun getString(context: Context): String =
    if (params.isEmpty()) {
      context.getString(id)
    } else {
      context.getString(id, *(params.toTypedArray()))
    }

}
