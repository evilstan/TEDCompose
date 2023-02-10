package com.evilstan.tedcompose.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.TopBarState

@Composable
fun SettingsScreen(setTopBar: (TopBarState) -> Unit) {
    setTopBar(getTopBarState(LocalContext.current))
    Column() {
        Text(text = "Nothing to set... yes", fontSize = 20.sp)
    }
}


@Composable
private fun getTopBarState(context: Context) =
    TopBarState(title = stringResource(R.string.trip_list),
        actions = {
            IconButton(onClick = {Toast.makeText(context, "Star clicked", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            IconButton(onClick = {Toast.makeText(context, "Everything already set", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        navigationIcon = null)