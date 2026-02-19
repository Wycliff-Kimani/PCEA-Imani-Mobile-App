package com.devcraft.pceaimani.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devcraft.pceaimani.ui.screens.Events.EventsScreen
import com.devcraft.pceaimani.ui.screens.Sermons.SermonsScreen
import com.devcraft.pceaimani.ui.screens.account.AccountScreen
import com.devcraft.pceaimani.ui.screens.bible.BibleScreen
import com.devcraft.pceaimani.ui.screens.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Sermons.route) {
            SermonsScreen()
        }

        composable(Screen.Bible.route) {
            BibleScreen()
        }

        composable(Screen.Events.route) {
            EventsScreen()
        }

        composable(Screen.Account.route) {
            AccountScreen()
        }
    }
}