package com.example.appbook2.data.api

import com.google.gson.annotations.SerializedName

data class BookDoc(
    @SerializedName("key") val key: String,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authorName: List<String>?,
    @SerializedName("cover_i") val coverId: Int?
) {
    fun getCoverUrl(): String {
        return if (coverId != null) {
            "https://covers.openlibrary.org/b/id/$coverId-M.jpg"
        } else {
            ""
        }
    }
}