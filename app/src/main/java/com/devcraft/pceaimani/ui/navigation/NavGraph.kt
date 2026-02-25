package com.devcraft.pceaimani.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devcraft.pceaimani.ui.screens.Events.EventsScreen
import com.devcraft.pceaimani.ui.screens.sermons.SermonsScreen
import com.devcraft.pceaimani.ui.screens.account.AccountScreen
import com.devcraft.pceaimani.ui.screens.bible.BibleScreen
import com.devcraft.pceaimani.ui.screens.home.HomeScreen
import com.devcraft.pceaimani.ui.screens.sermons.SermonDetailsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.Sermons.route) {
            SermonsScreen(
                onSermonClick = { sermonId ->
                    navController.navigate(Screen.SermonDetail.createRoute(sermonId))
                }
            )
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


        composable(
            route = Screen.SermonDetail.route,
            arguments = listOf(
                navArgument("sermonId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val sermonId = backStackEntry.arguments?.getString("sermonId") ?: ""

            SermonDetailsScreen(
                sermonId = sermonId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}