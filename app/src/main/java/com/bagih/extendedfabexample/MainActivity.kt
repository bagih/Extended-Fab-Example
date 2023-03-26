package com.bagih.extendedfabexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bagih.extendedfabexample.presentation.MainViewModel
import com.bagih.extendedfabexample.ui.theme.ExtendedFabExampleTheme

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExtendedFabExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            MainScreenFab(
                viewModel = viewModel,
                onLikeButtonClick = { viewModel.likeButtonClicked() }) {
                viewModel.FavButtonClicked()
            }
        }) {
        MainScreenContent(paddingValues = it)
    }
}

@Composable
fun MainScreenFab(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onLikeButtonClick: () -> Unit,
    onFavButtonClick: () -> Unit
) {
    val isLikeButtonPressed by viewModel.isLikeButtonPressed.collectAsState()
    val isFavButtonPressed by viewModel.isFavButtonPressed.collectAsState()

    Row(modifier = modifier) {
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.like_button)) },
            icon = {
                if (isLikeButtonPressed) {
                    Icon(
                        painter = painterResource(id = R.drawable.thumb_up_48px_fill),
                        contentDescription = stringResource(id = R.string.like_button_desc),
                        tint = Color.Blue
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.thumb_up_48px),
                        contentDescription = stringResource(id = R.string.like_button_desc)
                    )
                }
            },
            onClick = onLikeButtonClick
        )
        Spacer(modifier = modifier.width(2.dp))
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.fav_button)) },
            icon = {
                if (isFavButtonPressed) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite_48px_fill),
                        contentDescription = stringResource(id = R.string.fav_button_desc),
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite_48px),
                        contentDescription = stringResource(id = R.string.fav_button_desc)
                    )
                }
            },
            onClick = onFavButtonClick
        )
    }
}

@Composable
fun MainScreenContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding() + 8.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.selong_belanak_title), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.h6.fontSize)
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(stringResource(id = R.string.selong_belanak_url))
                .crossfade(true)
                .build(),
            contentDescription = stringResource(
                id = R.string.selong_belanak_title
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 8.dp),
            text = stringResource(id = R.string.selong_belanak_desc),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExtendedFabExampleTheme {
        Greeting("Android")
    }
}