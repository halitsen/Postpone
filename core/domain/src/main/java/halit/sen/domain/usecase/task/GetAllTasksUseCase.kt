package halit.sen.domain.usecase.task

import halit.sen.data.dto.Task
import halit.sen.domain.entity.TaskEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface GetAllTasksUseCase {
    operator fun invoke(task: Task): Flow<ResponseState<List<TaskEntity>>>
}