package com.example.keepnotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "note_table")
data class Note(
    @ColumnInfo(name = "note_text") var text : String
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
