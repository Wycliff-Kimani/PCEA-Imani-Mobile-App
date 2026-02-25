package com.devcraft.pceaimani.utils

import com.devcraft.pceaimani.data.model.Sermon

fun Sermon.formattedDate(): String {
    val timestamp = datePreached ?: createdAt
    if (timestamp == null) return "Date not available"

    val dateString = timestamp.toDate().toString()
    // Format: "Thu Jan 15 13:22 2026" (removes seconds and timezone)
    val parts = dateString.split(" ")
    return if (parts.size >= 4) {
        // Take day of week, month, day, time (without seconds), and year
        val timeParts = parts[3].split(":")
        val timeWithoutSeconds = if (timeParts.size >= 2) {
            "${timeParts[0]}:${timeParts[1]}"
        } else {
            parts[3]
        }
        "${parts[0]} ${parts[1]} ${parts[2]} $timeWithoutSeconds ${parts.last()}"
    } else {
        dateString
    }
}

