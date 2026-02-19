package com.devcraft.pceaimani.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bible
import compose.icons.fontawesomeicons.solid.CalendarAlt
import compose.icons.fontawesomeicons.solid.ChalkboardTeacher
import compose.icons.fontawesomeicons.solid.Home
import compose.icons.fontawesomeicons.solid.UserCircle

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Screen(
        route = "home",
        title = "Home",
        icon = FontAwesomeIcons.Solid.Home
    )

    object Sermons : Screen(
        route = "sermons",
        title = "Sermons",
        icon = FontAwesomeIcons.Solid.ChalkboardTeacher
    )

    object Bible : Screen(
        route = "bible",
        title = "Bible",
        icon = FontAwesomeIcons.Solid.Bible
    )

    object Events : Screen(
        route = "events",
        title = "Events",
        icon = FontAwesomeIcons.Solid.CalendarAlt
    )

    object Account : Screen(
        route = "account",
        title = "Account",
        icon = FontAwesomeIcons.Solid.UserCircle
    )
}
