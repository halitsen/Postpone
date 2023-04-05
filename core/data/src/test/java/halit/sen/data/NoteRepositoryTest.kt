package halit.sen.data

import com.google.common.truth.Truth.assertThat
import halit.sen.data.database.note.NoteDatabaseDao
import halit.sen.data.dto.Note
import halit.sen.data.repository.NoteRepository
import halit.sen.data.repository.NoteRepositoryImpl
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class NoteRepositoryTest {

    @Mock
    private lateinit var noteDatabaseDao: NoteDatabaseDao
    private lateinit var noteRepository: NoteRepository
    private val note = Note(0L, "test title", "test description")

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        noteRepository = NoteRepositoryImpl(noteDatabaseDao)
    }

    @Test
    fun getNote_whenDatabaseReturnSuccess_returnSuccess() {
        runBlocking {
            Mockito.`when`(noteDatabaseDao.getNote("0"))
                .thenReturn(flowOf(note))
            noteRepository.getNote("0").collectLatest {
                assertThat(it).isInstanceOf(ResponseState.Success::class.java)
            }
        }
    }

    @Test
    fun getNote_whenDatabaseReturnError_returnError() {
        runBlocking {
            Mockito.`when`(noteDatabaseDao.getNote("0"))
                .thenReturn(null)
            noteRepository.getNote("0").collectLatest {
                assertThat(it).isInstanceOf(ResponseState.Error::class.java)
            }
        }
    }

    @Test
    fun getAllNotes_whenDatabaseReturnSuccess_returnSuccess() {
        runBlocking {
            Mockito.`when`(noteDatabaseDao.getAllNotes())
                .thenReturn(flowOf(listOf(note)))
            noteRepository.getAllNotes().collectLatest {
                assertThat(it).isInstanceOf(ResponseState.Success::class.java)
            }
        }
    }

    @Test
    fun getAllNotes_whenDatabaseReturnError_returnError() {
        runBlocking {
            Mockito.`when`(noteDatabaseDao.getAllNotes())
                .thenReturn(null)
            noteRepository.getAllNotes().collectLatest {
                assertThat(it).isInstanceOf(ResponseState.Error::class.java)
            }
        }
    }

    @Test
    fun addNote_whenDatabaseReturnSuccess_returnSuccess() {
        runBlocking {
            Mockito.`when`(noteDatabaseDao.addNote(note))
                .thenAnswer {
                    ResponseState.Success(true)
                }
            val response = noteRepository.addNote(note)
            assertThat(response).isInstanceOf(ResponseState.Success::class.java)

        }
    }
}
