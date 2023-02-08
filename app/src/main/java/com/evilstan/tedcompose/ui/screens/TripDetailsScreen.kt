package com.evilstan.tedcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.Trip
import com.evilstan.tedcompose.ui.theme.ColorMain
import com.evilstan.tedcompose.ui.theme.ColorTextGrayActive
import com.evilstan.tedcompose.ui.theme.ColorTextGrayInactive
import com.evilstan.tedcompose.ui.theme.ColorTextWhite
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun TripDetails(trip: Trip? = null) { //TODO remove nullable

    val pagerState = rememberPagerState(pageCount = 2)
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        TopAppBar(backgroundColor = ColorMain, modifier = Modifier.height(56.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.trip_number, trip?.tripId ?: 9379992),
                        style = TextStyle(color = ColorTextWhite),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp)
                            .weight(1f),
                        textAlign = TextAlign.Center,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_trip_refresh),
                        contentDescription = null,
                        Modifier
                            .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                            .fillMaxHeight()
                            .clickable { })
                }
            }
        }
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(stringResource(id = R.string.stops), stringResource(id = R.string.documents))
    val scope = rememberCoroutineScope()
    TabRow(modifier = Modifier.height(48.dp),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = ColorTextWhite,
        contentColor = ColorTextWhite,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = ColorTextGrayActive
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index],
                        fontSize = 14.sp,
                        color = if (pagerState.currentPage == index) ColorTextGrayActive else ColorTextGrayInactive,
                        style = TextStyle(fontFamily = FontFamily.SansSerif)
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
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) {
            page ->
        when (page) {
            0 -> TripEventsList(size = 10)
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
            color = ColorTextGrayActive,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}