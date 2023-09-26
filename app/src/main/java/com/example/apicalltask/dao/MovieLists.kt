package com.example.apicalltask.dao

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "lists")
data class MovieLists(
    var title_name:String,
    var thumb_image:String,

): Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "listId")
    var id:Int = 0

}