package halit.sen.domain.usecase.task

import halit.sen.domain.entity.TaskEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface GetAllTasksUseCase {
    operator fun invoke(): Flow<ResponseState<List<TaskEntity>>>
}