package com.devcraft.pceaimani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.devcraft.pceaimani.ui.components.BottomNavigationBar
import com.devcraft.pceaimani.ui.components.ScaffoldTopBar
import com.devcraft.pceaimani.ui.navigation.AppNavGraph
import com.devcraft.pceaimani.ui.theme.PCEAImaniTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check permission for internet access and request if not granted
        if (checkSelfPermission(android.Manifest.permission.INTERNET) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.INTERNET), 1)
        }

        enableEdgeToEdge()
        setContent {

            // Check permission for internet access and request if not granted
            if (checkSelfPermission(android.Manifest.permission.INTERNET) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.INTERNET), 1)
            }

            val navController = rememberNavController()
            PCEAImaniTheme {
                Scaffold(
                    topBar = {
                        ScaffoldTopBar(navController)
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        AppNavGraph(navController)
                    }
                }
            }
        }
    }
}