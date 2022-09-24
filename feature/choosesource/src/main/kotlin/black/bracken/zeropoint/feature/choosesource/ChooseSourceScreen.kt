package black.bracken.zeropoint.feature.choosesource

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
private fun ChooseSourceScreen() {
  Box {
    Button(onClick = {}) {
      Text("sample")
    }
  }
}

@Preview
@Composable
private fun PreviewChooseSourceScreen() {
  ChooseSourceScreen()
}