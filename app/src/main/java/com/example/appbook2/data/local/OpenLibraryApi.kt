package com.example.appbook2.data.local

import com.example.appbook2.data.api.BookSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): Response<BookSearchResponse>
}