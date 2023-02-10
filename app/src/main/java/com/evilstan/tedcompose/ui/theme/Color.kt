package com.evilstan.tedcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val MaterialTheme.mainColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF424242) else Color(0xFF252D40)


val MaterialTheme.backgroundDispatched
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF153560) else Color(0xFFE3E9F2)
val MaterialTheme.backgroundEnRoute
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF574009) else Color(0xFFFAF4E4)
val MaterialTheme.backgroundCompleted
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF24692A) else Color(0xFFEEF7F2)
val MaterialTheme.backgroundCancelled
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF662525) else Color(0xFFFDF2F2)
val MaterialTheme.textDispatched @Composable get() = Color(0xFF2F80ED)
val MaterialTheme.textEnRoute @Composable get() = Color(0xFFF4BE34)
val MaterialTheme.textCancelled @Composable get() = Color(0xFFEB5757)
val MaterialTheme.textCompleted @Composable get() = Color(0xFF42BE4C)
val MaterialTheme.textDarkGray
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color(0xFF7E8184)
val MaterialTheme.textLightGray
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFB0B0B0) else Color(0xFFB0B0B0)
val MaterialTheme.textMain
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

@Composable
fun getBackgroundColor(status: String): Color =
    when (status) {
        "accepted",
        "dispatched" -> MaterialTheme.backgroundDispatched
        "en route",
        "at stop" -> MaterialTheme.backgroundEnRoute
        "completed" -> MaterialTheme.backgroundCompleted
        else -> MaterialTheme.backgroundCancelled
    }

@Composable
fun getTextColor(status: String): Color =
    when (status) {
        "accepted",
        "dispatched" -> MaterialTheme.textDispatched
        "en route",
        "at stop" -> MaterialTheme.textEnRoute
        "completed" -> MaterialTheme.textCompleted
        else -> MaterialTheme.textCancelled
    }

