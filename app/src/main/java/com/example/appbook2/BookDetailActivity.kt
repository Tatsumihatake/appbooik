package com.example.appbook2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // Sembunyikan ActionBar atas
        supportActionBar?.hide()

        // Inisialisasi View
        val ivCover = findViewById<ImageView>(R.id.ivDetailCover)
        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvAuthor = findViewById<TextView>(R.id.tvDetailAuthor)
        val btnStartReading = findViewById<Button>(R.id.btnStartReading)

        // Terima data dari Intent
        val bookTitle = intent.getStringExtra("BOOK_TITLE") ?: "Judul Tidak Diketahui"
        val bookCoverUrl = intent.getStringExtra("BOOK_COVER_URL") ?: ""
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR") ?: "Penulis Tidak Diketahui"

        // Tampilkan Data ke Layar
        tvTitle.text = bookTitle
        tvAuthor.text = bookAuthor

        if (bookCoverUrl.isNotEmpty()) {
            Glide.with(this)
                .load(bookCoverUrl)
                .placeholder(android.R.color.darker_gray)
                .into(ivCover)
        }

        // Aksi Tombol Mulai Membaca
        btnStartReading.setOnClickListener {
            // Nanti di Fase 4: Buka layar simulasi pembaca PDF/Teks
            Toast.makeText(this, "Membuka buku: $bookTitle...", Toast.LENGTH_SHORT).show()
        }
    }
}