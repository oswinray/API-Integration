package com.example.apicalltask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apicalltask.ConnectionLiveData
import com.example.apicalltask.data.dataclass
import com.example.apicalltask.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {
    var listRepo: ListRepository? = null
    var usersList = MutableLiveData<dataclass>()
    var networkStatus = MutableLiveData<Boolean>() // LiveData for network status

    fun fetchDataIfNetworkAvailable() {
        val isConnected = ConnectionLiveData(getApplication(),this).value ?: true

        if (isConnected) {
            // Network is available, fetch the data using a coroutine
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = listRepo?.itemList()
                    if (response != null && response.isSuccessful) {
                        networkStatus.postValue(isConnected)
                        usersList.postValue(response.body())
                    } else {
                        // Handle API error here
                    }
                } catch (e: Exception) {
                    // Handle other exceptions here
                }
            }
        }
    }
}

