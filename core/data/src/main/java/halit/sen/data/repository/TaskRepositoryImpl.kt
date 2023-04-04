package halit.sen.data.repository

import halit.sen.data.dto.Task
import halit.sen.postpone.common.ResponseState
import halit.sen.data.database.todo.TaskDatabaseDao
import halit.sen.data.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDatabaseDao: TaskDatabaseDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TaskRepository {
    override suspend fun addTask(task: Task): ResponseState<Boolean> {
        return withContext(ioDispatcher) {
            try {
                taskDatabaseDao.addTask(task)
                ResponseState.Success(true)
            } catch (e: Exception) {
                ResponseState.Error(e)
            }
        }
    }

    override suspend fun updateTask(task: Task): ResponseState<Boolean> {
        return withContext(ioDispatcher) {
            try {
                taskDatabaseDao.updateTask(task)
                ResponseState.Success(true)
            } catch (e: Exception) {
                ResponseState.Error(e)
            }
        }
    }

    override suspend fun deleteTask(task: Task): ResponseState<Boolean> {
       return withContext(ioDispatcher) {
           try {
               taskDatabaseDao.deleteTask(task)
               ResponseState.Success(true)
           } catch (e: Exception) {
               ResponseState.Error(e)
           }
        }
    }

    override suspend fun getAllTasks(): Flow<ResponseState<List<Task>>> = channelFlow {
           withContext(ioDispatcher) {
            try {
                taskDatabaseDao.getAllTasks().conflate().collectLatest {
                send(ResponseState.Success(it))
                }
            } catch (e: Exception) {
                send(ResponseState.Error(e))
            }
        }
    }
}