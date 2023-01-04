package com.example.postpone.repository

import com.example.postpone.data.note.NoteDatabaseDao
import com.example.postpone.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NoteRepository @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao
) {
    suspend fun addNote(note: Note) {
        noteDatabaseDao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDatabaseDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDatabaseDao.deleteNote(note)
    }

    suspend fun getNote(id: String): Flow<Note>{
        return noteDatabaseDao.getNote(id)
    }

    suspend fun deleteAllNotes() {
        noteDatabaseDao.deleteAllNotes()
    }

    fun getAllNotes(): Flow<List<Note>> {
        return noteDatabaseDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
    }
}