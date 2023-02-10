package com.evilstan.tedcompose.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.evilstan.tedcompose.R
import com.evilstan.tedcompose.data.TopBarState

@Composable
fun ContactsScreen(setTopBar: (TopBarState) -> Unit) {
    val selectedItem = rememberSaveable { mutableStateOf("Open menu") }
    setTopBar(getTopBarState(LocalContext.current, selectedItem))
    Column {
        Text(text = selectedItem.value, fontSize = 20.sp)
    }
}

@Composable
private fun getTopBarState(context: Context, bodyContent: MutableState<String>) =
    TopBarState(
        title = stringResource(R.string.trip_list),
        actions = {
            IconButton(onClick = { toast(context, "Driver 1 logged in") }) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            IconButton(onClick = { toast(context, "Nothing to build") }) {
                Icon(
                    imageVector = Icons.Filled.Build,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            TopAppBarDropdownMenu(bodyContent = bodyContent)
        },
        navigationIcon = null
    )

fun toast(context: Context, text: String) {
    Toast.makeText(
        context,
        text,
        Toast.LENGTH_SHORT
    ).show()

}

@Composable
fun TopAppBarDropdownMenu(bodyContent: MutableState<String>) {
    val expanded = rememberSaveable { mutableStateOf(false) }
    Box(
        Modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = {
            expanded.value = true
        }) {
            Icon(
                Icons.Filled.ArrowDropDown,
                tint = Color.White,
                contentDescription = null
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = "First Item Selected"
        }) {
            Text("First item")
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = "Second Item Selected"
        }) {
            Text("Second item")
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = "Third Item Selected"
        }) {
            Text("Third item")
        }

        Divider()

        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = "Fourth Item Selected"
        }) {
            Text("Fourth item")
        }
    }
}