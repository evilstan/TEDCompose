package com.evilstan.tedcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.TopBarState
import com.evilstan.tedcompose.data.Trip
import com.evilstan.tedcompose.ui.MainViewModel
import com.evilstan.tedcompose.ui.theme.*
import com.evilstan.tedcompose.utils.capitalizeWords


@Composable
fun TripList(onTripClick: (tripId: Long) -> Unit, setTopBar:(TopBarState) -> Unit) {
    setTopBar(getTopBarState())
    val viewModel: MainViewModel = viewModel()
    val tripList = remember { viewModel.getTripList() }

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(tripList) { trip ->
            TripItem(trip) { onTripClick(it) }
        }
    }
}


@Composable
fun TripItem(trip: Trip, onTripClick: (tripId: Long) -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(top = 32.dp)
            .clickable {
                onTripClick(trip.tripId)
            }, shape = RectangleShape
    ) {
        Column {
            Head(trip)
            Body(trip)
        }

    }
}

@Composable
fun Head(trip: Trip) {
    Row(
        modifier = Modifier
            .background(color = getBackgroundColor(status = trip.tripStatus))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.trip_number),
            color = MaterialTheme.textDarkGray,
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = trip.tripId.toString(),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .alignByBaseline(),
            color = MaterialTheme.textMain,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = trip.tripStatus.capitalizeWords(),
            color = getTextColor(status = trip.tripStatus),
            style = TextStyle(fontSize = 14.sp),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.alignByBaseline()
        )
    }

}

@Composable
fun Body(trip: Trip) {
    Row(modifier = Modifier.padding(top = 16.dp, start = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_clock_grey),
            contentDescription = null
        )
        Text(
            text = trip.dateFrom,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_point_right),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )
        Text(
            text = trip.dateTo,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
    Row(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
        Column(modifier = Modifier.padding(top = 20.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_route),
                contentDescription = null
            )
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = trip.eventList.first().city,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = trip.eventList.first().address,
                fontSize = 14.sp,
                color = MaterialTheme.textDarkGray
            )
            Text(
                text = trip.eventList.last().city,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                text = trip.eventList.last().address,
                fontSize = 14.sp,
                color = MaterialTheme.textDarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

    }
}

@Composable
private fun getTopBarState() =
    TopBarState(title = stringResource(R.string.trip_list),
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    tint = Color.White,
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_trip_refresh),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        navigationIcon = null)

@Preview(showBackground = true)
@Composable
fun Preview() {
    TEDComposeTheme {
        TripList ({}, {})
    }
}