package com.example.postpone.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postpone.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}