package halit.sen.domain.getNote

import app.cash.turbine.test
import com.google.common.truth.Truth
import halit.sen.domain.noteId
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class GetNoteUseCaseTest {

    private var getNoteUseCase = FakeGetNoteUseCase()

    @Test
    fun responseState_whenStateLoading_returnLoading() =
        runBlocking {
            getNoteUseCase(noteId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndSuccess_returnLoadingAndSuccess() = runBlocking {
        getNoteUseCase(noteId).test {
            Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
            Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun responseState_whenStateLoadingAndError_returnError() {
        runBlocking {
            getNoteUseCase.updateShowError(true)
            getNoteUseCase(noteId).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Error::class.java)
                awaitComplete()
            }
        }
    }
}