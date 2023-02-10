package com.evilstan.tedcompose.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.evilstan.tedcompose.data.TopBarState
import com.evilstan.tedcompose.ui.theme.mainColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun TopBar(topBarState: TopBarState, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        backgroundColor = MaterialTheme.mainColor,
        title = {
            Text(
                text = topBarState.title,
                style = TextStyle(color = Color.White),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            if (topBarState.navigationIcon != null) topBarState.navigationIcon.invoke()
            else IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        },
        actions = { topBarState.actions?.invoke(this) }
    )
}