package com.evilstan.tedcompose.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.TopBarState
import com.evilstan.tedcompose.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun TripDetails(tripId: Long, navController: NavHostController?, setTopBar: (TopBarState) -> Unit) {
    setTopBar(getTopBarState(navController, tripId))
    val pagerState = rememberPagerState(pageCount = 2)
    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, tripId)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(stringResource(id = R.string.stops), stringResource(id = R.string.documents))
    val scope = rememberCoroutineScope()
    TabRow(modifier = Modifier
        .padding(vertical = 16.dp)
        .height(48.dp),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = MaterialTheme.textDarkGray
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = list[index],
                        fontSize = 14.sp,
                        color = if (pagerState.currentPage == index) MaterialTheme.textDarkGray else MaterialTheme.textLightGray,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, tripId: Long) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> TripEventsList(tripId)
            1 -> TabContentScreen(data = "Nothing to do here (yet)")
        }
    }
}

@Composable
fun TabContentScreen(data: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.textDarkGray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun getTopBarState(navController: NavHostController?, tripId: Long) =
    TopBarState(title = stringResource(R.string.trip_number_arg, tripId),
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_trip_refresh),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        })

@OptIn(ExperimentalPagerApi::class)
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DetailsPreview() {
    TEDComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TripDetails(0, null){}        }
    }
}