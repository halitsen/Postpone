package halit.sen.domain.usecase.addTask

import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.usecase.task.AddTaskUseCase
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeAddTaskUseCase: AddTaskUseCase {
    private var showError = false
    fun shouldShowError(isError: Boolean){
        this.showError = isError
    }
    override fun invoke(taskEntity: TaskEntity): Flow<ResponseState<Boolean>> = flow{
        emit(ResponseState.Loading)
        if(showError){
            emit(ResponseState.Error(IOException()))
        }else{
            emit(ResponseState.Success(true))
        }
    }
}