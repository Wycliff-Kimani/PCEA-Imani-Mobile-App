package com.devcraft.pceaimani.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devcraft.pceaimani.R
import com.devcraft.pceaimani.ui.components.FeatureCard
import com.devcraft.pceaimani.ui.components.VerseTodayCard
import com.devcraft.pceaimani.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    val verseText = "The fear of the Lord is the beginning of wisdom; A good understanding have all those who do His commandments. His praise endures forever."
    val verseNumber = "Psalms 111:10"

    val features = listOf(
        Triple("Sermon Notes", R.drawable.notes_icon) { navController.navigate(Screen.Home.route) },
        Triple("Bible", R.drawable.bible_icon) { navController.navigate(Screen.Bible.route) },
        Triple("Live", R.drawable.live_icon) { navController.navigate(Screen.Home.route) },
        Triple("Sermons", R.drawable.sermons_icon) { navController.navigate(Screen.Sermons.route) },
        Triple("Prayer", R.drawable.prayer_icon) { navController.navigate(Screen.Home.route) },
        Triple("Events", R.drawable.events_icon) { navController.navigate(Screen.Events.route) }
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp)
    ) {
        // Verse of the Week Card
        VerseTodayCard(verseText, verseNumber)

        Spacer(modifier = Modifier.height(16.dp))

        // Discover Section Title
        Text(
            text = "Discover",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Feature Cards Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(features) { (title, icon, onClick) ->
                FeatureCard(
                    title = title,
                    icon = icon,
                    onClick = onClick
                )
            }
        }
    }
}