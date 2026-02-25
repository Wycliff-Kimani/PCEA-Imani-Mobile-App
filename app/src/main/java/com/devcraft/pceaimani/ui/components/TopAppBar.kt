package com.devcraft.pceaimani.ui.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApp(
    title: String = "PCEA Imani",
    subtitle: String? = null,          // optional
    showHomeSubtitle: Boolean = false  // for home screen only
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (showHomeSubtitle || subtitle != null) {
                    Text(
                        text = subtitle ?: "Your spiritual companion",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { /* notifications */ }) {
                Icon(Icons.Default.Notifications, "Notifications", tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = { /* account */ }) {
                Icon(Icons.Default.AccountCircle, "Account", tint = MaterialTheme.colorScheme.primary)
            }
        }
    )
}