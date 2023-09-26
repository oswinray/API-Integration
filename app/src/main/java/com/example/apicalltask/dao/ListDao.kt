package com.example.apicalltask.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ListDao {

    @Insert
    fun insertTask(list: MovieLists)

    @Delete
    fun delete(list:MovieLists)

    @Query("SELECT * FROM lists")
    fun getAll(): LiveData<List<MovieLists>>

}