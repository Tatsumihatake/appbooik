package com.example.appbook2.data.model
import androidx.room.*
import android.content.Context

@Database(entities = [ReadingProgress::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun progressDao(): ProgressDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "appbook_db").build().also { INSTANCE = it }
        }
    }
}