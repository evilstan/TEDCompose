package com.evilstan.tedcompose.data

data class Event(
    var eventId: Long,
    var sequence: Int,
    var event: String,
    var status: String,
    val orderNumber: String?,
    val truckNumber: String?,
    val trailerNumber: String?,
    val appointment: String,
    val city: String,
    val address: String,
    val shipper: String,
    val poNumber: String,
    val cargoDescription: String?,
    val cargoSpecs: String?,
    val driverStopInstructions: String?,
    val driverNote: String?
)
