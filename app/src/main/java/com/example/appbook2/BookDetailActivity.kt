package com.example.appbook2

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.appbook2.data.model.AppDatabase
import com.example.appbook2.data.model.ReadingProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookDetailActivity : AppCompatActivity() {
    private val reqPerm = registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            reqPerm.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val ch = NotificationChannel("FAV_CH", "Favorit", NotificationManager.IMPORTANCE_HIGH)
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(ch)
        }

        val title = intent.getStringExtra("BOOK_TITLE") ?: ""
        val cover = intent.getStringExtra("BOOK_COVER_URL") ?: ""
        val bookId = title.replace("\\s".toRegex(), "").lowercase()

        findViewById<TextView>(R.id.tvDetailTitle).text = title
        findViewById<TextView>(R.id.tvDetailAuthor).text = intent.getStringExtra("BOOK_AUTHOR")
        Glide.with(this).load(cover).into(findViewById(R.id.ivDetailCover))

        findViewById<Button>(R.id.btnStartReading).setOnClickListener {
            val input = EditText(this).apply { inputType = android.text.InputType.TYPE_CLASS_NUMBER; hint = "Halaman ke (Max 200)" }
            AlertDialog.Builder(this).setTitle("Simulasi Baca").setView(input)
                .setPositiveButton("Simpan") { _, _ ->
                    val page = input.text.toString().toIntOrNull() ?: 0
                    lifecycleScope.launch(Dispatchers.IO) {
                        AppDatabase.getDatabase(this@BookDetailActivity).progressDao().saveProgress(ReadingProgress(bookId, title, cover, 200, page, System.currentTimeMillis()))
                        withContext(Dispatchers.Main) { Toast.makeText(this@BookDetailActivity, "Tersimpan", Toast.LENGTH_SHORT).show() }
                    }
                }.show()
        }

        findViewById<Button>(R.id.btnFavorite).setOnClickListener {
            val notif = NotificationCompat.Builder(this, "FAV_CH")
                .setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Favorit!")
                .setContentText("$title ditambahkan ke Favorit").setPriority(NotificationCompat.PRIORITY_HIGH).setAutoCancel(true).build()
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(System.currentTimeMillis().toInt(), notif)
        }
    }
}