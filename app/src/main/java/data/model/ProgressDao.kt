package com.example.appbook2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProgressDao {
    // Menyimpan atau mengupdate progres buku
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: ReadingProgress)

    // Mengambil semua riwayat buku, diurutkan dari yang terakhir dibaca
    @Query("SELECT * FROM reading_progress ORDER BY lastReadTimestamp DESC")
    suspend fun getAllHistory(): List<ReadingProgress>

    // Mengambil progres satu buku spesifik
    @Query("SELECT * FROM reading_progress WHERE bookId = :id")
    suspend fun getProgressById(id: String): ReadingProgress?
}
