package com.example.appbook2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appbook2.R
import com.example.appbook2.data.api.BookDoc

class BookAdapter(private var bookList: List<BookDoc>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBookCover: ImageView = itemView.findViewById(R.id.ivBookCover)
        val tvBookTitle: TextView = itemView.findViewById(R.id.tvBookTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        // PERBAIKAN: Menggunakan R.layout.item_book, bukan R.id.item_book
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.tvBookTitle.text = book.title

        // Memuat gambar dari URL menggunakan Glide
        val coverUrl = book.getCoverUrl()
        if (coverUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(coverUrl)
                .placeholder(android.R.color.darker_gray)
                .into(holder.ivBookCover)
        } else {
            holder.ivBookCover.setImageResource(android.R.color.darker_gray)
        }
    }

    override fun getItemCount(): Int = bookList.size

    // Fungsi untuk memperbarui daftar saat API selesai mendownload
    fun updateData(newBooks: List<BookDoc>) {
        bookList = newBooks
        notifyDataSetChanged()
    }
}
