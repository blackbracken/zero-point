package black.bracken.zeropoint.feature.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import black.bracken.zeropoint.featurecommon.composable.AutoResizeText
import black.bracken.zeropoint.resource.R as ResR

@Composable
fun ChooseSourceScreen(
  viewModel: ChooseSourceViewModel,
) {
  Box(
    modifier = Modifier.fillMaxSize(),
  ) {
    Image(
      painter = painterResource(id = ResR.drawable.kayo_lineart),
      contentDescription = null,
      colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
      modifier = Modifier
        .fillMaxWidth()
        .alpha(0.25f)
        .align(Alignment.BottomEnd)
        .absoluteOffset(x = 96.dp)
    )

    Column(
      verticalArrangement = Arrangement.SpaceBetween,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 32.dp)
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Spacer(modifier = Modifier.height(96.dp))

        AutoResizeText(
          text = "ZERO-POINT",
          maxFontSize = 96.sp,
          fontFamily = FontFamily(Font(ResR.font.valorant)),
        )

        Spacer(modifier = Modifier.height(8.dp))

        AutoResizeText(
          text = "An unofficial valorant stats viewer",
          maxFontSize = 16.sp,
          fontFamily = FontFamily(Font(ResR.font.valorant)),
          textAlign = TextAlign.End,
          modifier = Modifier
            .fillMaxWidth(fraction = 0.75f)
            .align(Alignment.End),
        )
      }

      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = "データの取得先を選択してください"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.width(160.dp),
        ) {
          Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
          ) {
            Text(text = "リモートデータ")
          }

          Spacer(modifier = Modifier.height(16.dp))

          Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
          ) {
            Text(text = "フェイクデータ")
          }

          Spacer(modifier = Modifier.height(96.dp))
        }
      }
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