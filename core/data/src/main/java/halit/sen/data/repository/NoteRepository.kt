package halit.sen.data.repository

import halit.sen.data.dto.Note
import halit.sen.postpone.common.ResponseState

interface NoteRepository {

    suspend fun addNote(note: Note):ResponseState<Boolean>

    suspend fun getNote(id: String): ResponseState<Note>

    suspend fun updateNote(note: Note): ResponseState<Boolean>

    suspend fun deleteNote(note: Note): ResponseState<Boolean>

    suspend fun getAllNotes(): ResponseState<List<Note>>
}