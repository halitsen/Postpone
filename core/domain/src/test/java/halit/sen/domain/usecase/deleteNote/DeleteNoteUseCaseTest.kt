package halit.sen.domain.usecase.deleteNote

import app.cash.turbine.test
import com.google.common.truth.Truth
import halit.sen.domain.entity.NoteEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class DeleteNoteUseCaseTest {
    private var deleteNoteUseCase = FakeDeleteNoteUseCase()

    @Test
    fun responseState_whenStateLoading_returnLoading() =
        runBlocking {
            deleteNoteUseCase(NoteEntity("")).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndSuccess_returnLoadingAndSuccess() =
        runBlocking {
            deleteNoteUseCase(NoteEntity("")).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
                awaitComplete()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndError_returnError() =
        runBlocking {
            deleteNoteUseCase.updateShowError(true)
            deleteNoteUseCase(NoteEntity("")).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Error::class.java)
                awaitComplete()
            }
        }
}