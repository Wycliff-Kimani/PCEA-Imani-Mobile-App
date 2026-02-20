package com.devcraft.pceaimani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.devcraft.pceaimani.ui.components.BottomNavigationBar
import com.devcraft.pceaimani.ui.navigation.AppNavGraph
import com.devcraft.pceaimani.ui.theme.PCEAImaniTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            PCEAImaniTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) {paddingValues ->
                    AppNavGraph(paddingValues, navController)
                }
            }
        }
    }
}