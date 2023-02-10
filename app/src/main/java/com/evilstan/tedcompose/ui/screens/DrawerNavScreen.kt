package com.evilstan.tedcompose.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.evilstan.tedcompose.data.TopBarState

@Composable
fun DrawerNavigationScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    var topBarState by remember {
        mutableStateOf(TopBarState())
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(topBarState, scope, scaffoldState)
        },
        drawerGesturesEnabled = true,
        drawerContent = {
            DrawerBody(
                menuItems = drawerScreens,
                scaffoldState,
                scope
            ) {
                navController.navigate(it.id.name)
//                {
//                    popUpTo = navController.graph.startDestinationId
//                    launchSingleTop = true
//                }
            }
        }
    ) { innerPadding ->
        TEDriverApp(Modifier.padding(innerPadding), navController) { topBarState = it }
    }
}