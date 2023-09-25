package com.example.apicalltask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apicalltask.data.ApiClient
import com.example.apicalltask.data.ApiService
import com.example.apicalltask.data.dataclass
import retrofit2.Call
import retrofit2.Response

class ListRepository (private val apiService: ApiService) {
    suspend fun itemList(): Response<dataclass> {
        return apiService.getHomePage()
    }
}

