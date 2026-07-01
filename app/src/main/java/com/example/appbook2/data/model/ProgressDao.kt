package com.example.appbook2.data.model
import androidx.room.*

@Dao
interface ProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun saveProgress(progress: ReadingProgress)
    @Query("SELECT * FROM reading_progress ORDER BY lastReadTimestamp DESC") suspend fun getAllHistory(): List<ReadingProgress>
}