package com.example.appbook2.data.api

import com.google.gson.annotations.SerializedName

data class BookSearchResponse(
    @SerializedName("numFound") val numFound: Int,
    @SerializedName("docs") val books: List<BookDoc>
)