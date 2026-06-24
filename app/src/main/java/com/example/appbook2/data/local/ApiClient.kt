package com.example.appbook2.data.local

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "[https://openlibrary.org/](https://openlibrary.org/)"

    val instance: OpenLibraryApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OpenLibraryApi::class.java)
    }
}
