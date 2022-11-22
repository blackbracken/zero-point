package black.bracken.zeropoint.uishare.ext

import android.content.Context
import android.content.pm.PackageManager

// cf. https://stackoverflow.com/a/46848226
fun Context.rebirthApplication() {
  val packageManager: PackageManager = packageManager
  val intent = packageManager.getLaunchIntentForPackage(packageName)
  val componentName = requireNotNull(intent).component
  val mainIntent = android.content.Intent.makeRestartActivityTask(componentName)

  startActivity(mainIntent)
  Runtime.getRuntime().exit(0)
}