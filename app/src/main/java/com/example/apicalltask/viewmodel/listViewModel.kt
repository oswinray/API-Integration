package com.example.apicalltask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicalltask.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val repository = ListRepository()

    val homeContentLiveData = repository.getHomeContentLiveData()

    fun fetchHomeContent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchHomeContent()
        }
    }
}