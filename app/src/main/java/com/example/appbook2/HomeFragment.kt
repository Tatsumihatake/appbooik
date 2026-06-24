package com.example.appbook2

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
import com.example.appbook2.data.local.ApiClient // Pastikan import sesuai dengan struktur Anda
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
        // 1. Hubungkan file Kotlin ini dengan desain fragment_home.xml
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 2. Kaitkan variabel dengan ID di XML
        rvPopularBooks = view.findViewById(R.id.rvPopularBooks)
        progressBar = view.findViewById(R.id.progressBar)

        // 3. Setup RecyclerView (Arah *scroll* ke samping / Horizontal)
        rvPopularBooks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bookAdapter = BookAdapter(emptyList()) // Daftar awal kosong
        rvPopularBooks.adapter = bookAdapter

        // 4. Perintahkan aplikasi untuk mendownload data API
        fetchBooks()

        return view
    }

    private fun fetchBooks() {
        progressBar.visibility = View.VISIBLE // Munculkan lingkaran loading

        // Gunakan Coroutine (Jalan di latar belakang agar HP tidak freeze)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Mencari buku dengan kata kunci "Android" ke OpenLibrary
                val response = ApiClient.instance.searchBooks("Android")

                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE // Hilangkan loading
                    if (response.isSuccessful && response.body() != null) {
                        // Jika sukses, masukkan daftar buku ke dalam Adapter
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
