package com.example.ccc.network.api

import com.example.ccc.network.model.EmailModal
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface TempApi {
    @POST("aqonjvxa/")
    fun sendEmail(@Body email: EmailModal): Call<String>

}