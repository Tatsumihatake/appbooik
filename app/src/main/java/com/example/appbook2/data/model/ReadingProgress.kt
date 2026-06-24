package com.example.appbook2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_progress")
data class ReadingProgress(
    @PrimaryKey val bookId: String, // Diambil dari 'key' Open Library
    val title: String,
    val coverUrl: String,
    val totalPages: Int,
    var currentPage: Int,
    var lastReadTimestamp: Long // Untuk mengurutkan history terbaru
) {
    // Fungsi untuk menghitung persentase
    fun getProgressPercentage(): Int {
        if (totalPages == 0) return 0
        return ((currentPage.toDouble() / totalPages) * 100).toInt()
    }
}
