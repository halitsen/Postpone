package halit.sen.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import halit.sen.data.database.todo.TaskDatabase
import halit.sen.data.database.todo.TaskDatabaseDao
import halit.sen.data.dto.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDaoTest {

    private lateinit var database: TaskDatabase
    private lateinit var todoDao: TaskDatabaseDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).allowMainThreadQueries().build()

        todoDao = database.taskDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertNoteTest() = runTest {
        val todo = Task(1L,"This is test todo model ",false)
        val todo2 = Task(2L,"This is 2 test todo model ",false)

        todoDao.addTask(todo)
        todoDao.getAllTasks().test {
            val list = awaitItem()
            assert(list.contains(todo))
            assert(list.contains(todo2).not())
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateTodoTest() = runTest {
        val todo = Task(1L,"This is test todo model ",false)
        todoDao.addTask(todo)
        todo.description = "updated todo"
        todoDao.updateTask(todo)
        todoDao.getAllTasks().test{
            val todo2 = awaitItem()
            assert("updated todo" == todo2.first().description)
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteTodoTest() = runTest {
        val todo = Task(1L,"This is test todo model ",false)
        todoDao.addTask(todo)
        todoDao.deleteTask(todo)

        todo.description = "updated todo"
        todoDao.updateTask(todo)
        todoDao.getAllTasks().test{
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }
}