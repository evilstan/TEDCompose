package com.evilstan.tedcompose.ui

import androidx.lifecycle.ViewModel
import com.evilstan.tedcompose.data.Trip
import com.evilstan.tedcompose.domain.Repository
import com.evilstan.tedcompose.domain.RepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.evilstan.tedcompose.data.TripState

class MainViewModel() : ViewModel() {

    private val _tripState = MutableStateFlow(TripState())
    val state: StateFlow<TripState> = _tripState.asStateFlow()

    private val repository: Repository = RepositoryImpl
    fun setTripListSize(size: Int) {
        repository.setTripListSize(size)
    }

    fun getTripList() = repository.getTripList()
    fun getTipById(tripId: Long): Trip = repository.getTripById(tripId)


    fun setTripId(tripId: Long) {
        _tripState.update { it.copy(tripId = tripId) }
    }
}