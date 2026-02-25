package com.devcraft.pceaimani.ui.screens.sermons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.devcraft.pceaimani.data.model.Sermon

// Temporary duplicate of formattedDate() – move to shared file later
fun Sermon.formattedDate(): String {
    val timestamp = datePreached ?: createdAt
    return timestamp?.toDate()?.toString() ?: "Date not available"
    // This will show something like: "Thu Jan 15 13:22:20 GMT+03:00 2026"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SermonDetailsScreen(
    sermonId: String,
    viewModel: SermonDetailsViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val sermon by viewModel.sermon.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sermon") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to sermons list"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Error loading sermon",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = error ?: "Unknown issue")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.retry() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            sermon == null -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Sermon not found")
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
                    // Large cover image
                    AsyncImage(
                        model = currentSermon.coverImageUrl.takeIf { it.isNotBlank() }
                            ?: "https://via.placeholder.com/600x400/0D47A1/FFFFFF?text=PCEA+Imani+Sermon",
                        contentDescription = "Cover for ${currentSermon.title}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = currentSermon.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D47A1)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

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

                        Row(
                            modifier = Modifier.padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            if (currentSermon.durationMinutes > 0) {
                                AssistChip(
                                    onClick = { },
                                    label = { Text("${currentSermon.durationMinutes} min") },
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = Color(0xFFF57C00).copy(alpha = 0.1f),
                                        labelColor = Color(0xFFF57C00)
                                    )
                                )
                            }

                            if (currentSermon.language.isNotBlank()) {
                                AssistChip(
                                    onClick = { },
                                    label = { Text(currentSermon.language.uppercase()) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = currentSermon.description,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 28.sp
                        )

                        Spacer(modifier = Modifier.height(32.dp))

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
                                Button(onClick = { /* TODO: Implement playback */ }) {
                                    Text("Play Sermon")
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(64.dp))
                    }
                }
            }
        }
    }
}

