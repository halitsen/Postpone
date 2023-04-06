package halit.sen.detail

import androidx.lifecycle.SavedStateHandle
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.usecase.note.AddNoteUseCase
import halit.sen.domain.usecase.note.DeleteNoteUseCase
import halit.sen.domain.usecase.note.GetNoteUseCase
import halit.sen.domain.usecase.note.UpdateNoteUseCase
import halit.sen.postpone.common.ResponseState
import halit.sen.postpone.common.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException
import halit.sen.postpone.common.R as coreRes

class NoteDetailViewModelTest {

    @Mock
    private lateinit var getNoteUseCase: GetNoteUseCase

    @Mock
    private lateinit var addNoteUseCase: AddNoteUseCase

    @Mock
    private lateinit var updateNoteUseCase: UpdateNoteUseCase

    @Mock
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase


    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: NoteDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        savedStateHandle = SavedStateHandle().apply {
            set(idArg, noteEntityPathId)
        }
        viewModel = NoteDetailViewModel(
            getNoteUseCase,
            addNoteUseCase,
            updateNoteUseCase,
            deleteNoteUseCase,
            savedStateHandle
        )
    }

    @Test
    fun state_WhenUseCaseReturnsLoading_isLoading() =
        runBlocking {
            val resultList = listOf(ResponseState.Loading)
            whenever(getNoteUseCase.invoke(noteEntityPathId)).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf<ScreenState<NoteEntity>>(ScreenState.Loading)
            val job = launch {
                viewModel.noteDetailUiState.toList(listOfEmittedResult)
            }
            verify(getNoteUseCase).invoke(noteEntityPathId)
            assert(listOfEmittedResult.first() is ScreenState.Loading)
            job.cancel()
        }


    @Test
    fun state_WhenUseCaseReturnsLoadingAndSuccess_isLoadingAndSuccessSequentially() = runBlocking {
        val resultList = listOf(
            ResponseState.Loading, ResponseState.Success(noteEntity)
        )
        whenever(getNoteUseCase.invoke(noteEntityPathId)).thenReturn(
            resultList.asFlow()
        )

        val listOfEmittedResult =
            mutableListOf(ScreenState.Loading, ScreenState.Success(noteEntity))
        val job = launch {
            viewModel.noteDetailUiState.toList(listOfEmittedResult)
        }
        verify(getNoteUseCase).invoke(noteEntityPathId)
        assert(listOfEmittedResult[1] is ScreenState.Success)
        job.cancel()
    }


    @Test
    fun state_WhenUseCaseReturnsDownloadingAndError_isDownloadingAndErrorSequentially() =
        runBlocking {
            val resultList = listOf(
                ResponseState.Loading, ResponseState.Error(IOException())
            )
            whenever(getNoteUseCase.invoke(noteEntityPathId)).thenReturn(
                resultList.asFlow()
            )

            val listOfEmittedResult =
                mutableListOf<ScreenState<NoteEntity>>(
                    ScreenState.Loading,
                    ScreenState.Error(coreRes.string.error)
                )
            val job = launch {
                viewModel.noteDetailUiState.toList(listOfEmittedResult)
            }
            verify(getNoteUseCase).invoke(noteEntityPathId)
            assert(listOfEmittedResult[1] is ScreenState.Error)
            job.cancel()
        }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}