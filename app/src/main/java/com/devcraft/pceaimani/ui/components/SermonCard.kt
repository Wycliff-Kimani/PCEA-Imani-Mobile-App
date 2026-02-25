package com.devcraft.pceaimani.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devcraft.pceaimani.data.model.Sermon

// Safe date formatting for minSdk 24
fun Sermon.formattedDate(): String {
    val timestamp = datePreached ?: createdAt
    return timestamp?.toDate()?.toString() ?: "Date not available"
    // This shoudl show something lik Thu Jan 15 13:22:20 GMT+03:00 2026 r sth whatever aahhhhhghghgg
}

@Composable
fun SermonsCard(
    sermon: Sermon,
    onClick: (Sermon) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(sermon) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                AsyncImage(
                    model = sermon.coverImageUrl.takeIf { it.isNotBlank() }
                        ?: "https://via.placeholder.com/300x200/0D47A1/FFFFFF?text=PCEA+Imani",
                    contentDescription = "Sermon cover: ${sermon.title}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                    contentScale = ContentScale.Crop
                )

                if (sermon.audioUrl.isNotBlank() || sermon.videoUrl.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Color.Black.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play sermon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color(0xFFF57C00).copy(alpha = 0.7f), CircleShape)
                                .padding(8.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = sermon.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = Color(0xFF0D47A1),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = sermon.preacher,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFF57C00),
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = sermon.formattedDate(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (sermon.durationMinutes > 0) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFFF57C00).copy(alpha = 0.1f),
                            contentColor = Color(0xFFF57C00)
                        ) {
                            Text(
                                text = "${sermon.durationMinutes} min",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }

                    if (sermon.language.isNotBlank()) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.LightGray.copy(alpha = 0.5f),
                            contentColor = Color.DarkGray
                        ) {
                            Text(
                                text = sermon.language.uppercase(),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SermonsCardPreview() {
    val sample = Sermon(
        title = "Thanksgiving to the Almighty",
        preacher = "Rev. John Mwangi",
        datePreached = com.google.firebase.Timestamp.now(),
        durationMinutes = 45,
        language = "en",
        audioUrl = "example.com"
    )
    SermonsCard(sermon = sample)
}