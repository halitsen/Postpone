package halit.sen.postpone

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import halit.sen.postpone.data.note.NoteDatabase
import halit.sen.postpone.data.note.NoteDatabaseDao
import halit.sen.postpone.model.Note
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    private lateinit var database: NoteDatabase
    private lateinit var noteDao: NoteDatabaseDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()

        noteDao = database.noteDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertNoteTest() = runTest {
        val note = Note(id = 1L, "Test", "This is test note..", System.currentTimeMillis())
        val note2 = Note(id = 2L, "Test2", "This is test note..", System.currentTimeMillis())
        noteDao.insertNote(note)

        noteDao.getAllNotes().test {
            val list = awaitItem()
            assert(list.contains(note))
            assert(list.contains(note2).not())
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getNoteTest() = runTest {
        val note = Note(id = 1L, "Test", "This is test note..", System.currentTimeMillis())
        noteDao.insertNote(note)

        noteDao.getNote(note.id.toString()).test {
            val note2 = awaitItem()
            assert(note2 == note)
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateNoteTest() = runTest {
        val note = Note(id = 1L, "Test", "This is test note..", System.currentTimeMillis())
        noteDao.insertNote(note)

        note.description = "updated description"
        noteDao.updateNote(note)

        noteDao.getAllNotes().test {
            val list = awaitItem()
            assert(list[0].description == "updated description")
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteNoteTest() = runTest {
        val note = Note(id = 1L, "Test", "This is test note..", System.currentTimeMillis())
        noteDao.insertNote(note)
        noteDao.deleteNote(note)

        noteDao.getAllNotes().test {
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteAllNotesTest() = runTest {
        val note = Note(id = 1L, "Test", "This is test note..", System.currentTimeMillis())
        val note2 = Note(id = 2L, "Test2", "This is 2 test note..", System.currentTimeMillis())
        noteDao.insertNote(note)
        noteDao.insertNote(note2)

        noteDao.deleteAllNotes()

        noteDao.getAllNotes().test {
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }

    }
}