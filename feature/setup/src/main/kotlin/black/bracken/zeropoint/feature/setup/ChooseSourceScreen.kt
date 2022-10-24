package black.bracken.zeropoint.feature.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChooseSourceScreen(
  viewModel: ChooseSourceViewModel,
) {
  Column(
    verticalArrangement = Arrangement.Center,
  ) {
  }
  Box {
    Button(onClick = {}) {
      Text("sample")
    }
  }
}

@Preview
@Composable
private fun PreviewChooseSourceScreen() {
  ChooseSourceScreen(
    viewModel = ChooseSourceViewModel(),
  )
}