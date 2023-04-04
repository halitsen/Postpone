package halit.sen.data.repository

import halit.sen.data.database.note.NoteDatabaseDao
import halit.sen.data.di.IoDispatcher
import halit.sen.data.dto.Note
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteRepository {

    override suspend fun addNote(note: Note): ResponseState<Boolean> {
        return withContext(ioDispatcher) {
            try {
                noteDatabaseDao.addNote(note)
                ResponseState.Success(true)
            } catch (e: Exception) {
                ResponseState.Error(e)
            }
        }
    }

    override suspend fun updateNote(note: Note): ResponseState<Boolean> {
        return withContext(ioDispatcher) {
            try {
                noteDatabaseDao.updateNote(note)
                ResponseState.Success(true)
            } catch (e: Exception) {
                ResponseState.Error(e)
            }
        }
    }

    override suspend fun deleteNote(note: Note): ResponseState<Boolean> {
        return withContext(ioDispatcher) {
            try {
                noteDatabaseDao.deleteNote(note)
                ResponseState.Success(true)
            } catch (e: Exception) {
                ResponseState.Error(e)
            }
        }
    }

    override suspend fun getNote(id: String): ResponseState<Note> {
        return withContext(ioDispatcher) {
            try {
                val response = noteDatabaseDao.getNote(id)
                ResponseState.Success(response)
            } catch (e: Exception) {
                ResponseState.Error(e)
            }
        }
    }

    override suspend fun getAllNotes(): Flow<ResponseState<List<Note>>> = channelFlow {
        withContext(ioDispatcher) {
            try {
                noteDatabaseDao.getAllNotes().conflate().collectLatest {
                send(ResponseState.Success(it))
                }
            } catch (e: Exception) {
                send(ResponseState.Error(e))
            }
        }
    }
}