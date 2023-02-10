package com.evilstan.tedcompose.ui.screens

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evilstan.tedcompose.data.TopBarState
import com.evilstan.tedcompose.ui.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

enum class TEDriverScreens {
    TripList,
    TripDetails,
    Settings,
    Contacts
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TEDriverApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    setTopBar: (TopBarState) -> Unit
) {
    val viewModel: MainViewModel = viewModel()
    val uiState by viewModel.state.collectAsState()
    NavHost(
        navController = navController,
        startDestination = TEDriverScreens.TripList.name,
        modifier = modifier
    ) {
        composable(TEDriverScreens.TripList.name) {
            TripList({ onTripClick(it, viewModel, navController) }, { setTopBar(it) })
        }
        composable(TEDriverScreens.TripDetails.name) {
            TripDetails(uiState.tripId, navController) { setTopBar(it) }
        }
        composable(TEDriverScreens.Settings.name) {
            SettingsScreen(){ setTopBar(it) }
        }
        composable(TEDriverScreens.Contacts.name) {
            ContactsScreen(){ setTopBar(it) }
        }
    }
}


fun onTripClick(tripId: Long, viewModel: MainViewModel, navController: NavHostController) {
    viewModel.setTripId(tripId)
    navController.navigate(TEDriverScreens.TripDetails.name)
}