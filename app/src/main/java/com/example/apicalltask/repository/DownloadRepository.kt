package com.example.apicalltask.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.apicalltask.dao.ListDao
import com.example.apicalltask.dao.ListDatabase
import com.example.apicalltask.dao.MovieLists
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DownloadRepository(application: Application) {

    val allTask: LiveData<List<MovieLists>>?
    private var userDao: ListDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    init{
        val db: ListDatabase? = ListDatabase.getDatabase(application)
        userDao = db?.taskData()
        allTask = userDao?.getAll()
    }

    fun insertTask(newTask: MovieLists){
        coroutineScope.launch(Dispatchers.IO){
            asyncInsertTask(newTask)
        }
    }

    fun asyncInsertTask(task: MovieLists){
        userDao?.insertTask(task)
    }
    fun deleteTask(task:MovieLists){
        coroutineScope.launch(Dispatchers.IO){
            asyncDeleteTask(task)
        }
    }
    private fun asyncDeleteTask(task: MovieLists){
        // inserts task in database
        userDao?.delete(task)
    }



}