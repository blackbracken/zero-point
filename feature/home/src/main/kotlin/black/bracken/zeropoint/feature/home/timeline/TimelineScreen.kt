package black.bracken.zeropoint.feature.home.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import black.bracken.zeropoint.uishare.composable.LoadIndicatorCover
import black.bracken.zeropoint.uishare.composable.Skeleton
import black.bracken.zeropoint.uishare.theme.ZeroColorTokens
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun TimelineScreenCoordinator(
  viewModel: TimelineViewModel,
) {
  val uiState by viewModel.uiState.collectAsState()

  TimelineScreen(
    uiState = uiState,
  )
}

@Composable
fun TimelineScreen(
  uiState: TimelineUiState,
) {
  LoadIndicatorCover(
    isLoading = uiState.showsLoadingIndicator,
    modifier = Modifier.fillMaxSize(),
  ) {
    when (uiState) {
      is TimelineUiState.Loading -> {
        LoadingScreen()
      }

      is TimelineUiState.Success -> {
        SuccessScreen(
          riotId = uiState.riotId,
          tagline = uiState.tagline,
          iconImageUrl = uiState.playerIconUrl,
        )
      }

      else -> {}
    }
  }
  // TODO: else
}

@Composable
private fun LoadingScreen() {

  Column(
    modifier = Modifier.padding(horizontal = 16.dp)
  ) {
    Spacer(modifier = Modifier.height(24.dp))

    Box(modifier = Modifier.fillMaxSize()) {
      Skeleton(
        modifier = Modifier
          .fillMaxWidth()
          .height(120.dp)
          .clip(RoundedCornerShape(8.dp))
      )
    }
  }
}

@Composable
private fun SuccessScreen(
  riotId: String,
  tagline: String,
  iconImageUrl: String,
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp)
  ) {
    Spacer(modifier = Modifier.height(24.dp))

    PlayerStatusCard(
      riotId = riotId,
      tagline = tagline,
      iconImageUrl = iconImageUrl,
    )

    Spacer(modifier = Modifier.height(24.dp))

    LazyColumn {
      items(20) {
        MatchResultItem()
        Spacer(modifier = Modifier.height(8.dp))
      }
    }
  }
}

@Composable
fun PlayerStatusCard(
  riotId: String,
  tagline: String,
  iconImageUrl: String,
) {
  val imageUrl =
    "https://media.valorant-api.com/playercards/38defae8-4b79-f5cc-09c1-ceb5e109c4c9/smallart.png"//"https://images.dog.ceo/breeds/shiba/shiba-15.jpg"
  val tierUrl =
    "https://media.valorant-api.com/competitivetiers/564d8e28-c226-3180-6285-e48a390db8b1/20/largeicon.png"
  val fadeFaceUrl =
    "https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png"

  CompositionLocalProvider(
    LocalContentColor provides ZeroColorTokens.valorantRed,
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clip(RoundedCornerShape(8.dp))
        .background(ZeroColorTokens.valorantRed),
    ) {
      val color = LocalContentColor.current
      LaunchedEffect(Unit) { println("blackbracken $color") }
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
          .padding(16.dp),
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
              text = riotId,
              maxLines = 1,
              fontSize = 20.sp,
              overflow = TextOverflow.Ellipsis,
              color = MaterialTheme.colorScheme.onPrimary,
              modifier = Modifier
                .weight(1f, fill = false)
                .alignByBaseline(),
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
              text = "#$tagline",
              maxLines = 1,
              fontSize = 12.sp,
              color = Color.LightGray,
              modifier = Modifier.alignByBaseline(),
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          Row {
            Column {
              Text(
                text = "K/D: 0.97",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary,
              )

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = "HS: 21.0%",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary,
              )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
              Text(
                text = "K/D: 0.97",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary,
              )

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = "HS: 21.0%",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary,
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
                .data(iconImageUrl)
                .crossfade(true)
                .build(),
              contentDescription = null,
              contentScale = ContentScale.Crop,
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
            color = MaterialTheme.colorScheme.onPrimary,
          )
        }
      }
    }
  }
}

@Composable
private fun MatchResultItem() {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .height(64.dp)
      .clip(RoundedCornerShape(4.dp))
      .background(ZeroColorTokens.valorantWinner.copy(alpha = 0.15f)),
  ) {
    Box(
      modifier = Modifier
        .width(4.dp)
        .fillMaxHeight()
        .background(ZeroColorTokens.valorantWinner.copy(alpha = 0.8f))
    )

    AsyncImage(
      model = ImageRequest.Builder(LocalContext.current)
        .data("https://media.valorant-api.com/agents/dade69b4-4f5a-8528-247b-219e5a1facd6/displayicon.png")
        .crossfade(true)
        .build(),
      contentDescription = null,
      modifier = Modifier
        .fillMaxHeight()
        .aspectRatio(1.0f),
    )

    Spacer(modifier = Modifier.width(12.dp))

    Text(
      text = "15 / 10 / 3",
      fontSize = 16.sp,
    )

    Spacer(modifier = Modifier.width(16.dp))

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = "VICTORY",
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "13 - 0",
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
      )

      Spacer(modifier = Modifier.height(32.dp))


    }
  }
}

@Preview
@Composable
private fun PreviewTimelineScreen() {
}