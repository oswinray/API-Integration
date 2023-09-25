package com.example.apicalltask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicalltask.data.dataclass
import com.example.apicalltask.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    var listRepo: ListRepository? = null
    var usersList = MutableLiveData<dataclass>()

    suspend fun listOfItem() {
        val results = listRepo?.itemList()
        if (results != null && results.isSuccessful) {
            usersList.postValue(results.body())
        }
    }

}