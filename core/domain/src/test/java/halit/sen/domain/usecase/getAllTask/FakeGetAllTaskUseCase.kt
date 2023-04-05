package halit.sen.domain.usecase.getAllTask

import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.usecase.task.GetAllTasksUseCase
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeGetAllTaskUseCase : GetAllTasksUseCase {
    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(): Flow<ResponseState<List<TaskEntity>>> = flow {

        emit(ResponseState.Loading)
        if (showError) {
            emit(ResponseState.Error(IOException()))
        } else {
            emit(ResponseState.Success(listOf()))
        }
    }
}