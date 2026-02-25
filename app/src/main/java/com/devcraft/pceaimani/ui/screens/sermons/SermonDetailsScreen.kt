package com.devcraft.pceaimani.ui.screens.sermons

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.devcraft.pceaimani.data.model.Sermon

// Safe fallback date formatter
fun Sermon.formattedDate(): String {
    val timestamp = datePreached ?: createdAt
    return timestamp?.toDate()?.toString() ?: "Date not available"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SermonDetailsScreen(
    sermonId: String,
    viewModel: SermonDetailsViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    Log.d("Details", "Screen loaded with ID: $sermonId")

    val sermon by viewModel.sermon.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sermon") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFFF57C00))
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error loading sermon", color = Color.Red)
                        Text(error ?: "Unknown issue")
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = { viewModel.retry() }) { Text("Retry") }
                    }
                }
            }

            sermon == null -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Sermon not found for ID: $sermonId")
                }
            }

            else -> {
                val currentSermon = sermon!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = currentSermon.coverImageUrl.takeIf { it.isNotBlank() }
                            ?: "https://via.placeholder.com/600x400/0D47A1/FFFFFF?text=Sermon",
                        contentDescription = "Cover",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = currentSermon.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D47A1)
                        )

                        Text(
                            text = currentSermon.preacher,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFFF57C00)
                        )

                        Text(
                            text = currentSermon.formattedDate(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = currentSermon.description,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Player placeholder
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF57C00).copy(alpha = 0.1f))
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Audio / Video Player", fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(onClick = { /* TODO */ }) {
                                    Text("Play Sermon")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}