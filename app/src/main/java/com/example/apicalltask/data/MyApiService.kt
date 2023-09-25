package com.example.apicalltask.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("mediahome/api/v2/home_page")
    suspend fun getHomePage(): Response<dataclass>
}
