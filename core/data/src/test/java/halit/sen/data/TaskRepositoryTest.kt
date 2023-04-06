package halit.sen.data

import com.google.common.truth.Truth
import halit.sen.data.database.todo.TaskDatabaseDao
import halit.sen.data.dto.Task
import halit.sen.data.repository.TaskRepository
import halit.sen.data.repository.TaskRepositoryImpl
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

internal class TaskRepositoryTest {

    @Mock
    private lateinit var taskDatabaseDao: TaskDatabaseDao
    private lateinit var taskRepository: TaskRepository
    private val task = Task(0L, "test task", false)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        taskRepository = TaskRepositoryImpl(taskDatabaseDao)
    }

    @Test
    fun getAllTasks_whenDatabaseReturnSuccess_returnSuccess() {
        runBlocking {
            Mockito.`when`(taskDatabaseDao.getAllTasks())
                .thenReturn(flowOf(listOf(task)))
            taskRepository.getAllTasks().collectLatest {
                Truth.assertThat(it).isInstanceOf(ResponseState.Success::class.java)
            }
        }
    }

    @Test
    fun getAllTasks_whenDatabaseReturnError_returnError() {
        runBlocking {
            Mockito.`when`(taskDatabaseDao.getAllTasks())
                .thenReturn(null)
            taskRepository.getAllTasks().collectLatest {
                Truth.assertThat(it).isInstanceOf(ResponseState.Error::class.java)
            }
        }
    }

}