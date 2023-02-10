package com.evilstan.tedcompose.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.TopBarState

@Composable
fun ContactsScreen(setTopBar: (TopBarState) -> Unit) {
    setTopBar(getTopBarState(LocalContext.current))
    Column() {
        Text(text = "Vancouver, Canada", fontSize = 20.sp)
    }
}

@Composable
private fun getTopBarState(context: Context) =
    TopBarState(
        title = stringResource(R.string.trip_list),
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "Nothing to build",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Build,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        navigationIcon = null
    )