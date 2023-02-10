package com.evilstan.tedcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evilstan.tedcompose.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.res.painterResource
import com.evilstan.tedcompose.ui.theme.mainColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun DrawerBody(
    menuItems: List<MenuItem>,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    Column(modifier = Modifier.background(MaterialTheme.mainColor)) {
        Image(
            painter = painterResource(id = R.drawable.trackensure_logo),
            contentDescription = null
        )
        LazyColumn(
            modifier = modifier
        ) {
            items(menuItems) { item ->
                DrawerItem(
                    item,
                    modifier = modifier
                ) {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    onItemClick(item)
                }
            }
        }
    }
}

@Composable
fun DrawerItem(menuItem: MenuItem, modifier: Modifier = Modifier, onItemClick: (MenuItem) -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onItemClick(menuItem)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = menuItem.textId),
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Divider()
    }
}

data class MenuItem(
    val id: TEDriverScreens,
    val textId: Int
)

val drawerScreens = listOf(
    MenuItem(TEDriverScreens.TripList, R.string.trips),
    MenuItem(TEDriverScreens.Settings, R.string.settings),
    MenuItem(TEDriverScreens.Contacts, R.string.contacts),
)
