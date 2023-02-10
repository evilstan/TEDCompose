package com.evilstan.tedcompose.utils

import androidx.compose.ui.graphics.Color
import com.evilstan.tedcompose.data.Event
import com.evilstan.tedcompose.data.Trip
import com.evilstan.tedcompose.ui.theme.*
import java.util.*
import java.util.concurrent.ThreadLocalRandom


fun getTripList(size: Int): List<Trip> {
    val tripList = mutableListOf<Trip>()
    for (i in 0..size) {
        tripList.add(getRandomTrip())
    }
    return tripList

}

fun getRandomTrip() = Trip(
    ThreadLocalRandom.current().nextInt(100000, 1000000).toLong(),
    getRandomStatus(),
    "26 Dec 2023",
    "28 Dec 2023",
    getEventList(4)
)


fun getEventList(size: Int): List<Event> {
    val eventList = mutableListOf<Event>()
    for (i in 0..size) {
        eventList.add(getRandomEvent())
    }
    return eventList
}


fun getRandomEvent() =
    Event(
        ThreadLocalRandom.current().nextInt(1000000, 10000000).toLong(),
        ThreadLocalRandom.current().nextInt(1, 6),
        getRandomEventType(),
        getConsistentStatus(),
        getRandomString(7, 4, false)?.uppercase(),
        getRandomString(8, -1, true)?.uppercase(),
        getRandomString(8, -1, true)?.uppercase(),
        "26 Dec 2023 - 28 Dec 2023",
        getRandomCity(),
        getRandomStreet(),
        getRandomString(8)!!,
        getRandomString(25, -1, false)!!,
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        "24 pallets, 10 x 10 x 10 ft, 100500 lb",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco",
        getRandomString(100, -1, true)
    )

fun getRandomEventType() = when (ThreadLocalRandom.current().nextInt(1, 4)) {
    1 -> "pickup"
    2 -> "delivery"
    3 -> "trip start"
    else -> "trip finish"
}

private var lastStatus = 1

fun getConsistentStatus(): String {
    val result = when (lastStatus) {
        1 -> "dispatched"
        2 -> "accepted"
        3 -> "en route"
        4 -> "at stop"
        5 -> "completed"
        else -> "cancelled"
    }
    if (lastStatus == 6) lastStatus = 1 else lastStatus++
    return result
}

fun getRandomStatus() = when (ThreadLocalRandom.current().nextInt(1, 6)) {
    1 -> "dispatched"
    2 -> "accepted"
    3 -> "en route"
    4 -> "at stop"
    5 -> "completed"
    else -> "cancelled"
}

fun getRandomString(
    maxLength: Int,
    minLength: Int = -1,
    nullable: Boolean = false
): String? {
    val length = if (minLength > -1) ThreadLocalRandom.current().nextInt(minLength, maxLength)
    else maxLength
    val alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 "
    var result = ""
    for (i in 0 until length) {
        result += alphabet[(Random().nextInt(alphabet.length))]
    }
    return if (!nullable) result else {
        if (Math.random() > 0.5) result else null
    }
}

fun getRandomCity(): String {
    val cities = listOf(
        "Kyiv",
        "Kharkiv",
        "Odesa",
        "Dnipro",
        "Donetsk",
        "Zaporizhzhia",
        "Lviv",
        "Kryvyi Rih",
        "Mykolaiv",
        "Chernihiv",
        "Kherson",
        "Poltava",
        "Khmelnytskyi",
        "Cherkasy",
        "Chernivtsi",
        "Zhytomyr",
        "Sumy"
    )
    return cities[ThreadLocalRandom.current().nextInt(cities.size)]
}

fun getRandomStreet(): String {
    val streets = listOf(
        "Cheetham Hill Road",
        "Corporation Street",
        "Deansgate",
        "King Street",
        "Kingsway",
        "Market Street",
        "Mosley Street",
        "New Cathedral Street",
        "Oldham Street",
        "Oxford Road",
        "Peter Street, Manchester",
        "Portland Street",
        "Princess Street",
        "Quay Street",
        "Sackville Street",
        "Spring Gardens",
        "Whitworth Street",
        "Wilmslow Road"

    )
    return "${streets[ThreadLocalRandom.current().nextInt(streets.size)]}, ${ThreadLocalRandom.current().nextInt(100)}"
}
fun String.capitalizeWords(): String = split(" ").joinToString(" ") { word ->
    word.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }
}
