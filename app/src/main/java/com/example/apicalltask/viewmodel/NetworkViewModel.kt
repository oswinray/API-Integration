package com.example.apicalltask.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class NetworkViewModel : ViewModel() {
    private val _statusText = MutableLiveData<String>()
    val statusText: LiveData<String> = _statusText

    fun updateStatus(status: String) {
        _statusText.value = status
    }
}

