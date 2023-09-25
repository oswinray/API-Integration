package com.example.apicalltask.data


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.api.fanvu.tv/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}


