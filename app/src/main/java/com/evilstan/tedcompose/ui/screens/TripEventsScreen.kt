package com.evilstan.tedcompose.ui.screens

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.Event
import com.evilstan.tedcompose.ui.MainViewModel
import com.evilstan.tedcompose.ui.theme.*
import com.evilstan.tedcompose.utils.capitalizeWords
import java.util.*

@Composable
fun TripEventsList(tripId: Long) {
    val viewModel: MainViewModel = viewModel()
    val trip = remember { viewModel.getTipById(tripId) }
    LazyColumn {
        items(
            count = trip.eventList.size,
            key = { trip.eventList[it].eventId.toInt() }) {
            TripEventItem(trip.eventList[it])
        }
    }
}


@Composable
fun TripEventItem(event: Event) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val textColor = getTextColor(event.status)
    val backgroundColor = getBackgroundColor(event.status)
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(bottom = 16.dp),
        shape = RectangleShape
    ) {
        Column {
            Head(event, backgroundColor, textColor) { expanded = !expanded }
            AnimatedVisibility(visible = expanded, enter = expandVertically()) {
                Body(event, textColor)
            }
        }
    }
}

@Composable
fun Head(event: Event, backgroundColor: Color, textColor: Color, onClick: () -> Unit) {
    val context = LocalContext.current

    Spacer(modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
    )
    Column(modifier = Modifier
        .background(backgroundColor)
        .padding(horizontal = 32.dp, vertical = 16.dp)
        .clickable { onClick() }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "#${event.sequence} ${event.event.uppercase()}",
                modifier = Modifier.weight(1f),
                style = TextStyle(
                    color = textColor,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold
                ),
            )
            Text(
                text = event.status.replaceFirstChar { it.titlecase(Locale.getDefault()) },
                style = TextStyle(
                    color = textColor,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
        HeadText(stringResource(R.string.order_number), event.orderNumber, textColor)
        HeadText(stringResource(R.string.truck_number), event.truckNumber, textColor)
        HeadText(stringResource(R.string.trailer_number), event.trailerNumber, textColor)
        HeadText(stringResource(R.string.appointment), event.appointment, textColor)
        Divider(
            thickness = 1.dp, color = Color(0xFF7E8184),
            modifier = Modifier
                .padding(top = 8.dp)
                .height(1.dp)
        )
        Text(
            text = event.city,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = event.address,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
                color = textColor,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .weight(1f)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("geo:35.08326, -106.63566?q=35.08326, -106.63566")
                        )
                        startActivity(context, intent, null)
                    }
            )
            Image(
                painter = painterResource(R.drawable.ic_pin),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("geo:35.08326, -106.63566?q=35.08326, -106.63566")
                        )
                        startActivity(context, intent, null)
                    },
                colorFilter = ColorFilter.tint(textColor)
            )
        }
    }
}

@Composable
fun Body(event: Event, textColor: Color) {
    Column(
        modifier = Modifier.padding(
            start = 32.dp,
            end = 32.dp,
            top = 8.dp,
            bottom = 16.dp
        )
    ) {
        event.apply {
            BodyText(stringResource(R.string.shipper), shipper, 18.sp)
            BodyText(stringResource(R.string.po_number), poNumber, 18.sp)
            Divider(
                thickness = 2.dp, color = MaterialTheme.textLightGray,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 10.dp)
                    .height(1.dp)
            )
            BodyText(stringResource(R.string.cargo_description), cargoDescription)
            BodyText(stringResource(R.string.cargo_specs), cargoSpecs)
            BodyText(stringResource(R.string.driver_stop_instructions), driverStopInstructions)
            BodyText(stringResource(R.string.driver_note), driverNote)
        }
        if (event.status.lowercase() == "accepted" ||
            event.status.lowercase() == "dispatched" ||
            event.status.lowercase() == "en route" ||
            event.status.lowercase() == "at stop"
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = textColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
            ) {
                Text(
                    text = when (event.status.lowercase()) {
                        "dispatched" -> stringResource(R.string.accept).capitalizeWords()
                        "accepted" -> stringResource(R.string.start_driving).capitalizeWords()
                        "en route" -> stringResource(R.string.confirm_departure).capitalizeWords()
                        else -> stringResource(R.string.confirm_arrival).capitalizeWords()
                    },
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun HeadText(caption: String?, value: String?, textColor: Color) {
    if (caption == null || value == null) return

    Row {
        Text(
            text = caption,
            color = textColor,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = value,
            color = MaterialTheme.textMain,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp)
                .weight(1f)
        )
    }
}

@Composable
fun BodyText(caption: String?, value: String?, fontSize: TextUnit = 16.sp) {
    if (caption == null || value == null) return

    Row {
        Text(
            text = caption,
            color = MaterialTheme.textLightGray,
            fontSize = fontSize,
            modifier = Modifier
                .padding(top = 10.dp)
                .weight(0.4f)
        )
        Text(
            text = value,
            fontSize = fontSize,
            modifier = Modifier
                .padding(start = 8.dp, top = 10.dp)
                .weight(0.8f)
        )
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    TEDComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TripEventsList(0)
        }
    }
}