package com.evilstan.tedcompose.domain

import com.evilstan.tedcompose.data.Trip
import com.evilstan.tedcompose.utils.getTripList


object RepositoryImpl : Repository {
    private var tripListSize = 0
    private var tripList: List<Trip>? = null


    override fun setTripListSize(size: Int) {
        tripListSize = size
    }

    private fun createTripList() {
        tripList = getTripList(tripListSize)
    }

    override fun getTripList(): List<Trip> {
        if (tripList.isNullOrEmpty()) createTripList()
        return tripList!!
    }

    override fun getTripById(tripId: Long): Trip {
        return if (tripId != 0L) {
            if (tripList.isNullOrEmpty()) createTripList()
            tripList!!.first { it.tripId == tripId }
        } else getRandomTrip()
    }

    override fun getRandomTrip():Trip {
        if (tripList.isNullOrEmpty()) createTripList()
        return tripList!![0]
    }
}