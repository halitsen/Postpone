package halit.sen.data.repository

import halit.sen.data.dto.Task
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun addTask(task: Task): ResponseState<Boolean>

    suspend fun updateTask(task: Task): ResponseState<Boolean>

    suspend fun deleteTask(task: Task): ResponseState<Boolean>

    suspend fun getAllTasks(): Flow<ResponseState<List<Task>>>
}