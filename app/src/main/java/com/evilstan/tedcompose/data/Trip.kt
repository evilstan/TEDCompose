package com.evilstan.tedcompose.data

data class Trip(
    var tripId: Long,
    var tripStatus: String,
    var dateFrom: String,
    var dateTo: String,
    var eventList: List<Event>
)
