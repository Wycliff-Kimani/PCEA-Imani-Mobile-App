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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devcraft.pceaimani.R
import com.devcraft.pceaimani.ui.components.FeatureCard
import com.devcraft.pceaimani.ui.components.VerseTodayCard
import com.devcraft.pceaimani.ui.theme.lightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val verseText = "The fear of the Lord is the beginning of wisdom; A good understanding have all those who do His commandments. His praise endures forever."
    val verseNumber = "Psalms 111:10"

    val features = listOf(
        Pair("Sermon Notes", R.drawable.notes_icon),
        Pair("Bible", R.drawable.bible_icon),
        Pair("Live", R.drawable.live_icon),
        Pair("Sermons", R.drawable.sermons_icon),
        Pair("Prayer", R.drawable.prayer_icon),
        Pair("Events", R.drawable.events_icon)
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = "Welcome to PCEA Imani",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Your spiritual companion",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { /* TODO: Handle notifications click */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(
                    onClick = { /* TODO: Handle account click */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Account",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            // Verse of the Week Card
            VerseTodayCard(verseText, verseNumber)

            Spacer(modifier = Modifier.height(15.dp))

            // Discover Section Title
            Text(
                text = "Discover",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = lightBlue
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Feature Cards Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(features) { (title, icon) ->
                    FeatureCard(
                        title = title,
                        icon = icon
                    )
                }
            }
        }
    }
}