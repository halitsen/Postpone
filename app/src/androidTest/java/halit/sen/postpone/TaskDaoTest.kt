package halit.sen.postpone

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import halit.sen.postpone.data.todo.TodoDatabase
import halit.sen.postpone.data.todo.TodoDatabaseDao
import halit.sen.postpone.model.Todo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDaoTest {

    private lateinit var database: TodoDatabase
    private lateinit var todoDao: TodoDatabaseDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).allowMainThreadQueries().build()

        todoDao = database.todoDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertNoteTest() = runTest {
        val todo = Todo(1L,"This is test todo model ",false)
        val todo2 = Todo(2L,"This is 2 test todo model ",false)

        todoDao.insertTodo(todo)
        todoDao.getAllTodos().test {
            val list = awaitItem()
            assert(list.contains(todo))
            assert(list.contains(todo2).not())
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getTodoTest() = runTest{
        val todo = Todo(1L,"This is test todo model ",false)
        todoDao.insertTodo(todo)

        todoDao.getTodo(todo.id.toString()).test{
            val todo2 = awaitItem()
            assert(todo == todo2)
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateTodoTest() = runTest {
        val todo = Todo(1L,"This is test todo model ",false)
        todoDao.insertTodo(todo)

        todo.description = "updated todo"
        todoDao.updateTodo(todo)
        todoDao.getTodo(todo.id.toString()).test{
            val todo2 = awaitItem()
            assert("updated todo" == todo2.description)
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteTodoTest() = runTest {
        val todo = Todo(1L,"This is test todo model ",false)
        todoDao.insertTodo(todo)
        todoDao.deleteTodo(todo)

        todo.description = "updated todo"
        todoDao.updateTodo(todo)
        todoDao.getAllTodos().test{
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteAllTodosTest() = runTest {
        val todo = Todo(1L,"This is 1 test todo model ",false)
        val todo2 = Todo(2L,"This is 2 test todo model ",false)
        val todo3 = Todo(3L,"This is 3 test todo model ",false)
        todoDao.insertTodo(todo)
        todoDao.insertTodo(todo2)
        todoDao.insertTodo(todo3)
        todoDao.deleteAllTodos()

        todoDao.getAllTodos().test{
            val list = awaitItem()
            assert(list.isEmpty())
            cancel()
        }
    }
}