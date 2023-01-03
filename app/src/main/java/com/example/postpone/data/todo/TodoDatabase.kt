package com.example.postpone.data.todo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postpone.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDatabaseDao

}