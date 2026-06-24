package com.example.appbook2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbook2.data.api.BookDoc
import com.example.appbook2.data.local.ApiClient
import com.example.appbook2.ui.adapter.BookAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var rvPopularBooks: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        rvPopularBooks = view.findViewById(R.id.rvPopularBooks)
        progressBar = view.findViewById(R.id.progressBar)

        rvPopularBooks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        
        // Inisialisasi Adapter beserta aksi kliknya
        bookAdapter = BookAdapter(emptyList<BookDoc>()) { clickedBook ->
            // Buka Activity Detail
            val intent = Intent(requireContext(), BookDetailActivity::class.java)
            intent.putExtra("BOOK_TITLE", clickedBook.title)
            intent.putExtra("BOOK_COVER_URL", clickedBook.getCoverUrl())
            
            // Ambil nama penulis pertama jika ada
            val authorName = clickedBook.authorName?.firstOrNull() ?: "Penulis Tidak Diketahui"
            intent.putExtra("BOOK_AUTHOR", authorName)
            
            startActivity(intent)
        }
        
        rvPopularBooks.adapter = bookAdapter

        fetchBooks()

        return view
    }

    private fun fetchBooks() {
        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.instance.searchBooks("Android")

                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful && response.body() != null) {
                        val books = response.body()!!.books
                        bookAdapter.updateData(books)
                    } else {
                        Toast.makeText(requireContext(), "Gagal memuat data", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE
                    Log.e("HomeFragment", "Error fetching books: ${e.message}")
                    Toast.makeText(requireContext(), "Terjadi kesalahan jaringan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}