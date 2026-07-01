package com.example.appbook2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbook2.data.api.ApiClient
import com.example.appbook2.ui.adapter.BookAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val rvBooks = view.findViewById<RecyclerView>(R.id.rvPopularBooks)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val etSearch = view.findViewById<EditText>(R.id.etSearchQuery)
        val btnSearch = view.findViewById<Button>(R.id.btnSearch)

        rvBooks.layoutManager = LinearLayoutManager(requireContext())
        bookAdapter = BookAdapter(emptyList()) { clickedBook ->
            val intent = Intent(requireContext(), BookDetailActivity::class.java)
            intent.putExtra("BOOK_TITLE", clickedBook.title)
            intent.putExtra("BOOK_COVER_URL", clickedBook.getCoverUrl())
            intent.putExtra("BOOK_AUTHOR", clickedBook.authorName?.firstOrNull() ?: "Anonim")
            startActivity(intent)
        }
        rvBooks.adapter = bookAdapter

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString()
            if (query.isNotEmpty()) fetchBooks(query, progressBar)
        }
        fetchBooks("Android", progressBar) 
        return view
    }

    private fun fetchBooks(query: String, progress: ProgressBar) {
        progress.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.instance.searchBooks(query, 15)
                withContext(Dispatchers.Main) {
                    progress.visibility = View.GONE
                    if (response.isSuccessful) bookAdapter.updateData(response.body()?.books ?: emptyList())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { progress.visibility = View.GONE }
            }
        }
    }
}