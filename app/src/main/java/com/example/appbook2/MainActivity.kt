package com.example.appbook2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt // Import untuk fungsi toColorInt()

class MainActivity : AppCompatActivity() {

    // --- DATA SIMULASI RIWAYAT ---
    private val historyTitles = arrayOf(
        "Filosofi Teras",
        "Atomic Habits",
        "Belajar Kotlin Dasar"
    )
    private val historyProgress = intArrayOf(75, 40, 90)

    // Pastikan gambar 'gambar1' & 'gambar2' ada di res/drawable
    private val historyImages = intArrayOf(
        R.drawable.gambar1,
        R.drawable.gambar2,
        R.drawable.gambar1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sembunyikan bar atas agar desain lebih bersih
        supportActionBar?.hide()
    
        // 1. Inisialisasi Semua Halaman (Layout Konten)
        val pageHome = findViewById<LinearLayout>(R.id.page_home)
        val pageSearch = findViewById<LinearLayout>(R.id.page_search)
        val pageHistory = findViewById<LinearLayout>(R.id.page_notifications)
        val pageLibrary = findViewById<RelativeLayout>(R.id.page_library)

        // 2. Inisialisasi Semua Tombol Menu Bawah
        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navSearch = findViewById<ImageView>(R.id.nav_search)
        val navHistory = findViewById<ImageView>(R.id.nav_notifications)
        val navLibrary = findViewById<ImageView>(R.id.nav_library)

        // 3. Inisialisasi List Riwayat (Halaman 3)
        val lvHistory = findViewById<ListView>(R.id.lvHistory)
        lvHistory.adapter = HistoryAdapter()

        // Fungsi bantuan untuk reset warna icon
        fun resetNavColor() {
            // Menggunakan KTX extension toColorInt() sesuai saran IDE
            val gray = "#808080".toColorInt()
            navHome.setColorFilter(gray)
            navSearch.setColorFilter(gray)
            navHistory.setColorFilter(gray)
            navLibrary.setColorFilter(gray)
        }

        // --- 4. LOGIKA NAVIGASI (Klik & Pindah Halaman) ---

        navHome.setOnClickListener {
            resetNavColor()
            navHome.setColorFilter(Color.BLACK)
            pageHome.visibility = View.VISIBLE
            pageSearch.visibility = View.GONE
            pageHistory.visibility = View.GONE
            pageLibrary.visibility = View.GONE
        }

        navSearch.setOnClickListener {
            resetNavColor()
            navSearch.setColorFilter(Color.BLACK)
            pageHome.visibility = View.GONE
            pageSearch.visibility = View.VISIBLE
            pageHistory.visibility = View.GONE
            pageLibrary.visibility = View.GONE
        }

        navHistory.setOnClickListener {
            resetNavColor()
            navHistory.setColorFilter(Color.BLACK)
            pageHome.visibility = View.GONE
            pageSearch.visibility = View.GONE
            pageHistory.visibility = View.VISIBLE
            pageLibrary.visibility = View.GONE
        }

        navLibrary.setOnClickListener {
            resetNavColor()
            navLibrary.setColorFilter(Color.BLACK)
            pageHome.visibility = View.GONE
            pageSearch.visibility = View.GONE
            pageHistory.visibility = View.GONE
            pageLibrary.visibility = View.VISIBLE
        }
    }

    // --- ADAPTER UNTUK HALAMAN RIWAYAT ---
    private inner class HistoryAdapter : ArrayAdapter<String>(this, R.layout.item_history, historyTitles) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val row = convertView ?: layoutInflater.inflate(R.layout.item_history, parent, false)

            val img = row.findViewById<ImageView>(R.id.imgHistoryBook)
            val title = row.findViewById<TextView>(R.id.tvHistoryTitle)
            val progressText = row.findViewById<TextView>(R.id.tvHistoryProgress)
            val progressBar = row.findViewById<ProgressBar>(R.id.pbHistory)
            val btn = row.findViewById<Button>(R.id.btnContinue)

            title.text = historyTitles[position]
            progressText.text = "Progres: ${historyProgress[position]}%"
            progressBar.progress = historyProgress[position]

            // Perbaikan error unresolved reference:
            // Kita pakai R.drawable.gambar1 sebagai fallback jika gagal load
            try {
                img.setImageResource(historyImages[position])
            } catch (e: Exception) {
                img.setImageResource(R.drawable.gambar1)
            }

            btn.setOnClickListener {
                Toast.makeText(this@MainActivity, "Membuka ${historyTitles[position]}...", Toast.LENGTH_SHORT).show()
            }

            return row
        }
    }
}