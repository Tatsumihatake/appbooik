package com.example.appbook2

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan logika ini ke activity_main.xml di Canvas
        setContentView(R.layout.activity_main)

        // Menyembunyikan title bar atas agar sesuai desain "Hi Fela"
        supportActionBar?.hide()

        // 1. Deklarasi dan kenali semua Halaman berdasarkan ID di XML
        val pageHome = findViewById<LinearLayout>(R.id.page_home)
        val pageSearch = findViewById<LinearLayout>(R.id.page_search)
        val pageLibrary = findViewById<RelativeLayout>(R.id.page_library)

        // 2. Deklarasi dan kenali semua Tombol Menu Bawah
        val navHome = findViewById<ImageView>(R.id.nav_home)
        val navSearch = findViewById<ImageView>(R.id.nav_search)
        val navLibrary = findViewById<ImageView>(R.id.nav_library)

        // 3. Logika Navigasi (Menampilkan dan Menyembunyikan Layout)

        // Jika tombol Home ditekan
        navHome.setOnClickListener {
            pageHome.visibility = View.VISIBLE
            pageSearch.visibility = View.GONE
            pageLibrary.visibility = View.GONE
        }

        // Jika tombol Search ditekan
        navSearch.setOnClickListener {
            pageHome.visibility = View.GONE
            pageSearch.visibility = View.VISIBLE
            pageLibrary.visibility = View.GONE
        }

        // Jika tombol Library ditekan
        navLibrary.setOnClickListener {
            pageHome.visibility = View.GONE
            pageSearch.visibility = View.GONE
            pageLibrary.visibility = View.VISIBLE
        }
    }
}