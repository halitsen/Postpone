package halit.sen.domain.usecase.task

import halit.sen.data.dto.Task
import halit.sen.data.repository.TaskRepository
import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.mapper.PostponeBaseMapper
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTasksUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val mapper: PostponeBaseMapper<List<Task>, List<TaskEntity>>
): GetAllTasksUseCase {
    override fun invoke(task: Task): Flow<ResponseState<List<TaskEntity>>> = flow{
        emit(ResponseState.Loading)
        when(val response = repository.getAllTasks()){
            is ResponseState.Error -> {
                emit(response)
            }
            is ResponseState.Success -> {
                emit(ResponseState.Success(mapper.map(response.data)))
            }
        }
    }
}