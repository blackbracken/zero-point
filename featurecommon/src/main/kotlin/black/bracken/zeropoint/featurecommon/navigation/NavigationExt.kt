package black.bracken.zeropoint.featurecommon.navigation

import androidx.navigation.NavController

fun NavController.navigate(navigator: Navigator<NoArg>) {
  navigator.navigate(this, NoArg)
}

fun <P> NavController.navigate(navigator: Navigator<P>, param: P) {
  navigator.navigate(this, param)
}
