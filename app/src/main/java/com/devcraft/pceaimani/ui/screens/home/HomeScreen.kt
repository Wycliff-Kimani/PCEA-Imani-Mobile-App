package com.devcraft.pceaimani.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devcraft.pceaimani.R
import com.devcraft.pceaimani.ui.components.FeatureCard
import com.devcraft.pceaimani.ui.theme.White
import com.devcraft.pceaimani.ui.theme.lightBlue

@Composable
fun HomeScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // Header Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Hello Wycliff",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = lightBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Good Morning!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account Icon",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Verse of the Week Card
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF4A4A73)),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.verse_of_the_week_icon),
                        contentDescription = "Verse of the Week Icon",
                        modifier = Modifier.size(55.dp)
                    )

                    Text(
                        text = "Verse of the Week",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "The fear of the Lord is the beginning of wisdom; A good understanding have all those who do His commandments. His praise endures forever.",
                    fontSize = 16.sp,
                    color = White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Psalms 111:10",
                    fontSize = 18.sp,
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

        // Row 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            FeatureCard(
                title = "Sermon Notes",
                icon = R.drawable.notes_icon,
                modifier = Modifier.weight(1f)
            )

            FeatureCard(
                title = "Bible",
                icon = R.drawable.bible_icon,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FeatureCard(
                title = "Live",
                icon = R.drawable.live_icon,
                modifier = Modifier.weight(1f)
            )

            FeatureCard(
                title = "Sermons",
                icon = R.drawable.sermons_icon,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FeatureCard(
                title = "Prayer",
                icon = R.drawable.prayer_icon,
                modifier = Modifier.weight(1f)
            )

            FeatureCard(
                title = "Events",
                icon = R.drawable.events_icon,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}