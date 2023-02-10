package com.evilstan.tedcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.evilstan.tedcompose.ui.MainViewModel
import com.evilstan.tedcompose.ui.screens.DrawerNavigationScreen
import com.evilstan.tedcompose.ui.screens.TripEventsList
import com.evilstan.tedcompose.ui.theme.TEDComposeTheme
import com.evilstan.tedcompose.ui.theme.mainColor

const val DEFAULT_TRIP_LIST_SIZE = 3
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setTripListSize(DEFAULT_TRIP_LIST_SIZE)
        setContent {
            TEDComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface
                ) {
                    window.statusBarColor = MaterialTheme.mainColor.toArgb()
                    DrawerNavigationScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TEDComposeTheme {
        TripEventsList(10)
    }
}