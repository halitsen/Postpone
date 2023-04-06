package halit.sen.domain.usecase.addTask

import app.cash.turbine.test
import com.google.common.truth.Truth
import halit.sen.domain.entity.TaskEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AddTaskUseCaseTest {
    private val addTaskUseCase = FakeAddTaskUseCase()

    @Test
    fun responseState_whenStateLoading_returnLoading() =
        runBlocking {
            addTaskUseCase(TaskEntity()).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndSuccess_returnLoadingAndSuccess() =
        runBlocking {
            addTaskUseCase(TaskEntity()).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
                awaitComplete()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndError_returnLoadingAndError() =
        runBlocking {
            addTaskUseCase(TaskEntity()).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
                awaitComplete()
            }
        }
}