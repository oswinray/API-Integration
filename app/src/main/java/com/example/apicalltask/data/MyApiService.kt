package com.example.apicalltask.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("mediahome/api/v2/home_page")
    fun getHomePage(): Call<dataclass>
}
