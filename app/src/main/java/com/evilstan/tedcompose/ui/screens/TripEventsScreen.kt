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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.core.content.ContextCompat.startActivity
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.Event
import com.evilstan.tedcompose.ui.theme.ColorTextBlack
import com.evilstan.tedcompose.ui.theme.ColorTextGrayInactive
import com.evilstan.tedcompose.ui.theme.TEDComposeTheme
import com.evilstan.tedcompose.utils.getColor
import com.evilstan.tedcompose.utils.getEventList
import java.util.*

@Composable
fun TripEventsList(size: Int) {
    LazyColumn {
        items(getEventList(10)) {
            TripEventItem(it)
        }
    }
}

@Composable
fun TripEventItem(event: Event) {

    val context = LocalContext.current
    var expanded by rememberSaveable { mutableStateOf(false) }
    val textColor = getColor(event.status).first
    val backgroundColor = getColor(event.status).second
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
    )

    Column(
        Modifier
            .background(backgroundColor)
            .clickable { expanded = !expanded }) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#${event.sequence} ${event.event.uppercase()}",
                Modifier
                    .padding(start = 30.dp, top = 16.dp)
                    .weight(1f),
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
                ),
                modifier = Modifier.padding(end = 30.dp, top = 16.dp)
            )
        }
        HeaderText(
            caption = stringResource(id = R.string.order_number),
            value = event.orderNumber,
            status = event.status
        )
        HeaderText(
            caption = stringResource(id = R.string.truck_number),
            value = event.truckNumber,
            status = event.status
        )
        HeaderText(
            caption = stringResource(id = R.string.trailer_number),
            value = event.trailerNumber,
            status = event.status
        )
        HeaderText(
            caption = stringResource(id = R.string.appointment),
            value = event.appointment,
            status = event.status
        )
        Divider(
            thickness = 1.dp, color = Color(0xFF7E8184),
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, top = 8.dp)
                .height(1.dp)
        )
        Text(
            text = event.city,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 32.dp, top = 8.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = event.address,
                fontSize = 16.sp,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline,
                    color = textColor
                ),
                modifier = Modifier
                    .padding(start = 32.dp, top = 8.dp, bottom = 16.dp)
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
                painter = painterResource(id = R.drawable.ic_pin),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 8.dp, end = 32.dp, bottom = 16.dp)
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
    AnimatedVisibility(visible = expanded, enter = expandVertically()) {
        Column{
            event.apply {
                BodyText(stringResource(id = R.string.shipper),shipper, 18)
                BodyText(stringResource(id = R.string.po_number),poNumber, 18)
                Divider(
                    thickness = 2.dp, color = ColorTextGrayInactive,
                    modifier = Modifier
                        .padding(start = 32.dp, end = 32.dp, top = 20.dp, bottom = 10.dp)
                        .height(1.dp)
                )
                BodyText(stringResource(id = R.string.cargo_description),cargoDescription)
                BodyText(stringResource(id = R.string.cargo_specs),cargoSpecs)
                BodyText(stringResource(id = R.string.driver_stop_instructions),driverStopInstructions)
                BodyText(stringResource(id = R.string.driver_note),driverNote)
            }
        }
    }
}



@Composable
fun HeaderText(caption: String?, value: String?, status: String) {
    if (caption == null || value == null) return
    val captionTextStyle = TextStyle(
        color = getColor(status).first,
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif
    )

    val valueTextStyleTED = TextStyle(
        color = ColorTextBlack,
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif
    )

    Row() {
        Text(
            text = caption,
            style = captionTextStyle,
            modifier = Modifier.padding(start = 30.dp, top = 4.dp)
        )
        Text(
            text = value,
            style = valueTextStyleTED,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp).weight(1f)
        )
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun BodyText(caption: String?, value: String?, fontSize: Int = 16) {
    if (caption == null || value == null) return

    val bodyTextStyleCaption = TextStyle(
        fontSize = TextUnit(fontSize.toFloat(),TextUnitType.Sp),
        color = ColorTextGrayInactive
    )
    val bodyTextStyleValue = TextStyle(
        fontSize = TextUnit(fontSize.toFloat(),TextUnitType.Sp),
    )

    Row {
        Text(
            text = caption,
            style = bodyTextStyleCaption,
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp)
                .weight(0.4f)
        )
        Text(
            text = value,
            style = bodyTextStyleValue,
            modifier = Modifier
                .padding(start = 8.dp, top = 10.dp, end = 30.dp)
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
            TripEventsList(25)
        }
    }
}