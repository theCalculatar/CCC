package com.example.ccc.network

import com.example.ccc.network.api.TempApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmailService {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    fun emailService(): TempApi {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())  // Scalars converter for String responses
            .client(client)
            .build()

        val api: TempApi = retrofit.create(TempApi::class.java)

        return api
    }
}