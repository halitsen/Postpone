package com.example.postpone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.postpone.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from note_table")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * from note_table where id = :id")
    suspend fun getNote(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE from note_table")
    suspend fun deleteAllNotes()

    @Delete()
    suspend fun deleteNote(note: Note)

}