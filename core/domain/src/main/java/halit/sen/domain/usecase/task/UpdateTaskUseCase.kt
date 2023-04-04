package halit.sen.domain.usecase.task

import halit.sen.data.dto.Task
import halit.sen.domain.entity.TaskEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface UpdateTaskUseCase {
    operator fun invoke(taskEntity: TaskEntity): Flow<ResponseState<Boolean>>
}