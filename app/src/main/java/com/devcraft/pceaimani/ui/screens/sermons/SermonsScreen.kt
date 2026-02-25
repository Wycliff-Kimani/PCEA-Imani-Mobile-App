package com.devcraft.pceaimani.ui.screens.sermons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devcraft.pceaimani.ui.components.SermonsCard
import com.devcraft.pceaimani.ui.components.TopApp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SermonsScreen(
    viewModel: SermonsViewModel = viewModel(),
    onSermonClick: (String) -> Unit = {}  // We'll change to navigate later
) {
    val sermons by viewModel.sermons.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TopApp(title = "Sermons")

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color(0xFFF57C00)  // flame accent
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Loading sermons...\nPreparing the preached Word",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFF757575)
                        )
                    }
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Something went wrong",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = error ?: "Unknown error",
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { viewModel.fetchSermons() },  // retry
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF57C00)
                            )
                        ) {
                            Text("Try Again")
                        }
                    }
                }
            }

            sermons.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No sermons available yet.\nCheck back after the next service.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 8.dp
                    )
                ) {
                    items(
                        items = sermons,
                        key = { it.id }
                    ) { sermon ->
                        SermonsCard(
                            sermon = sermon,
                            onClick = { onSermonClick(sermon.id) }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}