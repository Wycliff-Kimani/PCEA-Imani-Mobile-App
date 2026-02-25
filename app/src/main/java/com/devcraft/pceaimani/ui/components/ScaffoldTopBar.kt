package com.devcraft.pceaimani.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.devcraft.pceaimani.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar(navController: NavController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screen.Home.route

    // Determine if we should show the top bar and what content to display
    val (showTitle, showSubtitle, showActions) = when (currentRoute) {
        Screen.Home.route -> Triple("PCEA Imani", "Your spiritual companion", true)
        Screen.Sermons.route -> Triple("Sermons", null, false)
        Screen.Bible.route -> Triple("Bible", null, false)
        Screen.Events.route -> Triple("Events", null, false)
        Screen.Account.route -> Triple("Account", null, false)
        else -> Triple("", null, false)
    }

    // Only show the top bar for main navigation screens, not for detail screens
    if (currentRoute in listOf(Screen.Home.route, Screen.Sermons.route, Screen.Bible.route, Screen.Events.route, Screen.Account.route)) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = showTitle,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    if (showSubtitle != null) {
                        Text(
                            text = showSubtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            actions = {
                if (showActions) {
                    IconButton(onClick = { /* notifications */ }) {
                        Icon(Icons.Default.Notifications, "Notifications", tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = { navController.navigate(Screen.Account.route) }) {
                        Icon(Icons.Default.AccountCircle, "Account", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        )
    }
}

