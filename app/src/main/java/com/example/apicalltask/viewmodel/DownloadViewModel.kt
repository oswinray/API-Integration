package com.example.apicalltask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.apicalltask.dao.MovieLists
import com.example.apicalltask.repository.DownloadRepository

class DownloadViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DownloadRepository = DownloadRepository(application)
    private val allTasks: LiveData<List<MovieLists>>? = repository.allTask


    fun insertTask(task: MovieLists){
        repository.insertTask(task)
    }

    fun deleteTask(task:MovieLists){
        repository.deleteTask(task)
    }


    fun getAllTask(): LiveData<List<MovieLists>>?{
        return allTasks

    }



}