package com.example.postpone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "description")
    val description: String="",
    @ColumnInfo(name = "is_done")
    var isDone: Boolean = false
)