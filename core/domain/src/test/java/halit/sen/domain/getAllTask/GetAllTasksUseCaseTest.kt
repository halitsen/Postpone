package halit.sen.domain.getAllTask

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import halit.sen.domain.getAllNotes.FakeGetAllNotesUseCase
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetAllTasksUseCaseTest {

    private val getAllTasksUseCase = FakeGetAllNotesUseCase()

    @Test
    fun responseState_whenStateLoading_returnLoading() = runBlocking {
        getAllTasksUseCase().test {
            assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun responseState_whenStateLoadingAndSuccess_returnLoadingAndSuccess() = runBlocking {
        getAllTasksUseCase().test {
            assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
            awaitComplete()
        }
    }
    @Test
    fun responseState_whenStateLoadingAndError_returnLoadingAndError() = runBlocking {
        getAllTasksUseCase.updateShowError(true)
        getAllTasksUseCase().test {
            assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(ResponseState.Error::class.java)
            awaitComplete()
        }
    }
}