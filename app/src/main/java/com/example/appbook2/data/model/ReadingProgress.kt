package com.example.appbook2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_progress")
data class ReadingProgress(
    @PrimaryKey val bookId: String,
    val title: String,
    val coverUrl: String,
    val totalPages: Int,
    var currentPage: Int,
    var lastReadTimestamp: Long
) {
    fun getProgressPercentage(): Int = if (totalPages == 0) 0 else ((currentPage.toDouble() / totalPages) * 100).toInt()
}
