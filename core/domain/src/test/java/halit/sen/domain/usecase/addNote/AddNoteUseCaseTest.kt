package halit.sen.domain.usecase.addNote

import app.cash.turbine.test
import com.google.common.truth.Truth
import halit.sen.domain.entity.NoteEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AddNoteUseCaseTest {
    private val addNoteUseCase = FakeAddNoteUseCase()

    @Test
    fun responseState_whenStateLoading_returnLoading() =
        runBlocking {
            addNoteUseCase(NoteEntity("")).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndSuccess_returnLoadingAndSuccess() =
        runBlocking {
            addNoteUseCase(NoteEntity("")).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
                awaitComplete()
            }
        }

    @Test
    fun responseState_whenStateLoadingAndError_returnLoadingAndError() =
        runBlocking {
            addNoteUseCase(NoteEntity("")).test {
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Loading::class.java)
                Truth.assertThat(awaitItem()).isInstanceOf(ResponseState.Success::class.java)
                awaitComplete()
            }
        }
}