package com.example.apicalltask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apicalltask.data.ApiClient
import com.example.apicalltask.data.ApiService
import retrofit2.Response

class ListRepository (private val apiService: ApiService) {
    suspend fun searchByTerm(term:String): Response<UserModelClass> {
        return GithubApi.searchPodcastsByTerm(term)
    }
}

