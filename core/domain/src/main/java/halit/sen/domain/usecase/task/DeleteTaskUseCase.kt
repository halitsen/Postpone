package halit.sen.domain.usecase.task

import halit.sen.data.dto.Task
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface DeleteTaskUseCase {

    operator fun invoke(task: Task): Flow<ResponseState<Boolean>>
}