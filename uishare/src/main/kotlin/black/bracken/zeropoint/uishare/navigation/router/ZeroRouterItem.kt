package black.bracken.zeropoint.uishare.navigation.router

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import black.bracken.zeropoint.uishare.navigation.NavRoute
import black.bracken.zeropoint.uishare.util.StringResource
import black.bracken.zeropoint.resource.R as ResR

enum class ZeroRouterItem(
  val route: NavRoute,
  val label: StringResource,
  val imageVector: ImageVector,
) {
  Timeline(
    route = NavRoute("fake"), // FIXME: NavRoute
    label = StringResource(ResR.string.bottom_nav_label_timeline),
    imageVector = Icons.Filled.Home,
  ),
}