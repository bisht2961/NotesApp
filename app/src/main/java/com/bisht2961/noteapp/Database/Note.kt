package com.bisht2961.noteapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "description") val description:String)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}