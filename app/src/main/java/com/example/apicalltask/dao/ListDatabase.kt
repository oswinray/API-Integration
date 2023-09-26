package com.example.apicalltask.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(MovieLists::class)], version = 1)
abstract class ListDatabase: RoomDatabase() {
    abstract fun taskData():ListDao

    companion object{
        private var INSTANCE: ListDatabase?=null

        internal fun getDatabase(context: Context):ListDatabase?{
            if(INSTANCE == null){
                synchronized(ListDatabase::class.java){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder<ListDatabase>(
                            context.applicationContext,
                            ListDatabase::class.java,
                            "list_Database"
                        ).build()
                    }
                }

            }
            return INSTANCE
        }
    }
}