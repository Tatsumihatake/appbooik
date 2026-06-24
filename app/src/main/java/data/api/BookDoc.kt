package com.example.appbook2.data.model

import com.google.gson.annotations.SerializedName

data class BookDoc(
    @SerializedName("key") val key: String,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authorName: List<String>?,
    @SerializedName("cover_i") val coverId: Int?
) {
    // Fungsi bantuan untuk mendapatkan URL gambar cover
    fun getCoverUrl(): String {
        return if (coverId != null) {
            "[https://covers.openlibrary.org/b/id/$coverId-M.jpg](https://covers.openlibrary.org/b/id/$coverId-M.jpg)" // M untuk Medium size
        } else {
            "" // Kembalikan string kosong atau URL gambar placeholder default
        }
    }
}