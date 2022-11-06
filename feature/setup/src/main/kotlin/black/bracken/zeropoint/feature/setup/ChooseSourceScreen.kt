package black.bracken.zeropoint.feature.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import black.bracken.zeropoint.resource.R as ResR

@Composable
fun ChooseSourceScreen(
  viewModel: ChooseSourceViewModel,
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize(),
  ) {
    Text(text = "hello?")
    Image(
      painter = painterResource(id = ResR.drawable.zeropoint_icon),
      contentDescription = null,
      modifier = Modifier
        .size(128.dp)
        .background(Color.Gray)
        .clip(CircleShape)
    )
  }
}

@Preview
@Composable
private fun PreviewChooseSourceScreen() {
  ChooseSourceScreen(
    viewModel = ChooseSourceViewModel(),
  )
}