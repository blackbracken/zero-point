package black.bracken.zeropoint.uishare.navigation.router

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import black.bracken.zeropoint.uishare.navigation.NavRoute
import black.bracken.zeropoint.uishare.navigation.direction.HomeDirection
import black.bracken.zeropoint.uishare.util.StringResource
import black.bracken.zeropoint.resource.R as ResR

enum class ZeroRouterItem(
  val route: NavRoute,
  val label: StringResource,
  val imageVector: ImageVector,
) {
  Home(
    route = HomeDirection.Root.destination,
    label = StringResource(ResR.string.bottom_navigation_label_home),
    imageVector = Icons.Filled.Home,
  ),
}