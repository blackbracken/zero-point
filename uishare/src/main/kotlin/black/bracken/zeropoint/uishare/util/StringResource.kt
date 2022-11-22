package black.bracken.zeropoint.uishare.util

import android.content.Context
import androidx.annotation.StringRes

data class StringResource(
  @StringRes val id: Int,
  val params: List<Any>,
) {

  constructor(@StringRes id: Int, vararg params: Any) : this(id, params.toList())

  fun getString(context: Context): String =
    if (params.isEmpty()) {
      context.getString(id)
    } else {
      context.getString(id, *(params.toTypedArray()))
    }

}
