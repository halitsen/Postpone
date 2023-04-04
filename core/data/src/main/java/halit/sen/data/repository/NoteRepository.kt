package halit.sen.data.repository

import halit.sen.data.dto.Note
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(note: Note):ResponseState<Boolean>

    suspend fun getNote(id: String): ResponseState<Note>

    suspend fun updateNote(note: Note): ResponseState<Boolean>

    suspend fun deleteNote(note: Note): ResponseState<Boolean>

    suspend fun getAllNotes(): Flow<ResponseState<List<Note>>>
}