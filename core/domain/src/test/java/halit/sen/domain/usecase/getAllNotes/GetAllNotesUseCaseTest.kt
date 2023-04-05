package halit.sen.domain.usecase.getAllNotes

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class GetAllNotesUseCaseTest {
    private var getAllNotesUseCase = FakeGetAllNotesUseCase()

    @Test
    fun responseState_whenStateLoading_returnLoading() =
        runBlocking {
            getAllNotesUseCase().test {
                assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
    @Test
    fun responseState_whenStateLoadingAndSuccess_returnLoadingAndSuccess() =
        runBlocking {
            getAllNotesUseCase().test {
                assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
                awaitComplete()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndError_returnError() =
        runBlocking {
            getAllNotesUseCase.updateShowError(true)
            getAllNotesUseCase().test {
                assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(ResponseState.Error::class.java)
                awaitComplete()
            }
        }
}