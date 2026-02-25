package com.devcraft.pceaimani.data.model

import com.google.firebase.Timestamp

data class Sermon(
    val id: String = "",
    val title: String = "",
    val preacher: String = "",
    val description: String = "",
    val audioUrl: String = "",
    val videoUrl: String = "",
    val coverImageUrl: String = "",
    val durationMinutes: Int = 0,
    val language: String = "",
    val createdAt: Timestamp? = null,
    val datePreached: Timestamp? = null
)