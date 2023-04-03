package halit.sen.domain.usecase.task

import halit.sen.data.dto.Task
import halit.sen.data.repository.TaskRepository
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : AddTaskUseCase {
    override operator fun invoke(task: Task): Flow<ResponseState<Boolean>> = flow {
        emit(ResponseState.Loading)

        when(val response = repository.addTask(task)) {
            is ResponseState.Error -> {
                emit(response)
            }
            is  ResponseState.Success -> {
                emit(ResponseState.Success(true))
            }
            else -> Unit
        }
    }
}