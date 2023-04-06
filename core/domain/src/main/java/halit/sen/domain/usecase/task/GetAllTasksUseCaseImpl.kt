package halit.sen.domain.usecase.task

import halit.sen.data.dto.Task
import halit.sen.data.repository.TaskRepository
import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.mapper.PostponeBaseMapper
import halit.sen.domain.mapper.PostponeListMapper
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTasksUseCaseImpl @Inject constructor(
    private val repository: TaskRepository,
    private val mapper: PostponeListMapper<Task, TaskEntity>
): GetAllTasksUseCase {
    override fun invoke(): Flow<ResponseState<List<TaskEntity>>> = flow{
        emit(ResponseState.Loading)

        repository.getAllTasks().collect { response ->
            when(response){
                is ResponseState.Error -> {
                    emit(response)
                }
                is ResponseState.Success -> {
                    emit(ResponseState.Success(mapper.map(response.data)))
                }
                else -> Unit
            }
        }
    }
}