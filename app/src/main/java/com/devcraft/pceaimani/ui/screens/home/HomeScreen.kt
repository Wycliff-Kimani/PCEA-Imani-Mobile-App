package com.devcraft.pceaimani.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devcraft.pceaimani.R
import com.devcraft.pceaimani.ui.components.FeatureCard
import com.devcraft.pceaimani.ui.theme.White
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
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
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

        Spacer(modifier = Modifier.height(12.dp))

        // Verse of the Week Card
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = White,
                                shape = MaterialTheme.shapes.small
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.verse_of_the_week_icon),
                            contentDescription = null,
                            modifier = Modifier.size(54.dp)
                        )
                    }


                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Verse of the Week",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = verseText,
                    color = White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "- $verseNumber",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}