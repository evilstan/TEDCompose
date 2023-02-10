package com.evilstan.tedcompose.domain

import com.evilstan.tedcompose.data.Trip

interface Repository {
    fun setTripListSize(size: Int)
    fun getTripList(): List<Trip>
    fun getTripById(tripId: Long): Trip
    fun getRandomTrip(): Trip
}