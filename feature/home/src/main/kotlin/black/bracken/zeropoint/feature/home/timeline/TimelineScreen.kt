package black.bracken.zeropoint.feature.home.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun TimelineScreenCoordinator(
  viewModel: TimelineViewModel,
) {
  TimelineScreen()
}

@Composable
fun TimelineScreen() {
  Column(
    modifier = Modifier.padding(horizontal = 16.dp)
  ) {
    Spacer(modifier = Modifier.height(24.dp))

    PlayerStatusCard()
  }
}

@Composable
fun PlayerStatusCard() {
  val imageUrl =
    "https://media.valorant-api.com/playercards/38defae8-4b79-f5cc-09c1-ceb5e109c4c9/smallart.png"//"https://images.dog.ceo/breeds/shiba/shiba-15.jpg"
  val tierUrl =
    "https://media.valorant-api.com/competitivetiers/564d8e28-c226-3180-6285-e48a390db8b1/20/largeicon.png"
  val fadeFaceUrl =
    "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png"

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(IntrinsicSize.Min)
      .clip(RoundedCornerShape(8.dp))
      .background(
        color = MaterialTheme.colorScheme.secondaryContainer,
      )
  ) {
    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data(fadeFaceUrl)
        .crossfade(true)
        .build(),
      contentDescription = null,
      alpha = 0.25f,
      modifier = Modifier
        .fillMaxHeight()
        .offset(x = (-8).dp)
        .align(Alignment.CenterStart),
    )

    Row(
      horizontalArrangement = Arrangement.End,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .wrapContentSize()
        .align(Alignment.CenterEnd)
        .padding(16.dp)
    ) {
      Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End,
        modifier = Modifier.weight(1f, fill = false),
      ) {
        Row(
          verticalAlignment = Alignment.Bottom
        ) {
          Text(
            text = "deadbeddddddef",
            maxLines = 1,
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
              .weight(1f, fill = false)
              .alignByBaseline()
          )

          Spacer(modifier = Modifier.width(2.dp))

          Text(
            text = "#glhff",
            maxLines = 1,
            fontSize = 12.sp,
            modifier = Modifier.alignByBaseline(),
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
          Column {
            Text(
              text = "K/D: 0.97",
              fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = "HS: 21.0%",
              fontSize = 16.sp,
            )
          }

          Spacer(modifier = Modifier.width(16.dp))

          Column {
            Text(
              text = "K/D: 0.97",
              fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = "HS: 21.0%",
              fontSize = 16.sp,
            )
          }
        }
      }

      Spacer(modifier = Modifier.width(16.dp))

      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Box {
          AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
              .data(imageUrl)
              .crossfade(true)
              .build(),
            contentDescription = null,
            modifier = Modifier
              .size(80.dp)
              .align(Alignment.Center)
              .clip(CircleShape)
              .border(
                width = 4.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                shape = CircleShape,
              ),
          )
          AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
              .data(tierUrl)
              .crossfade(true)
              .build(),
            contentDescription = null,
            modifier = Modifier
              .size(40.dp)
              .align(Alignment.BottomEnd),
          )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
          text = "Diamond 3",
          textAlign = TextAlign.Center,
        )
      }
    }
  }
}

@Preview
@Composable
private fun PreviewTimelineScreen() {
}