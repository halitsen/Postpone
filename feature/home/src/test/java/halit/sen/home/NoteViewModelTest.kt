package halit.sen.home

import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.usecase.note.DeleteNoteUseCase
import halit.sen.domain.usecase.note.GetAllNotesUseCase
import halit.sen.home.note.NoteViewModel
import halit.sen.postpone.common.ResponseState
import halit.sen.postpone.common.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

class NoteViewModelTest {

    @Mock
    private lateinit var getAllNotesUseCase: GetAllNotesUseCase

    @Mock
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase

    private lateinit var viewModel: NoteViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = NoteViewModel(
            getAllNotesUseCase,
            deleteNoteUseCase
        )
    }

    @Test
    fun getAllNotesState_WhenUseCaseReturnsLoading_isLoading() {
        runBlocking {
            val resultList = listOf(ResponseState.Loading)
            whenever(getAllNotesUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf<ScreenState<List<NoteEntity>>>(ScreenState.Loading)

            val job = launch {
                viewModel.noteUiState.toList(listOfEmittedResult)
            }
            verify(getAllNotesUseCase).invoke()
            assert(listOfEmittedResult.first() is ScreenState.Loading)
            job.cancel()
        }
    }

    @Test
    fun getAllNotesState_WhenUseCaseReturnsLoadingAndSuccess_isLoadingAndSuccessSequentially() {
        runBlocking {
            val resultList = listOf(
                ResponseState.Loading, ResponseState.Success(noteEntityList)
            )
            whenever(getAllNotesUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf(ScreenState.Loading, ScreenState.Success(noteEntityList))
            val job = launch {
                viewModel.noteUiState.toList(listOfEmittedResult)
            }
            verify(getAllNotesUseCase).invoke()
            assert(listOfEmittedResult[1] is ScreenState.Success)
            job.cancel()
        }
    }

    @Test
    fun getAllNotesState_WhenUseCaseReturnsLoadingAndError_isLoadingAndErrorSequentially() {
        runBlocking {
            val resultList = listOf(
                ResponseState.Loading, ResponseState.Error(IOException())
            )
            whenever(getAllNotesUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf<ScreenState<List<NoteEntity>>>(
                    ScreenState.Loading,
                    ScreenState.Error(coreRes.string.error)
                )
            val job = launch {
                viewModel.noteUiState.toList(listOfEmittedResult)
            }
            verify(getAllNotesUseCase).invoke()
            assert(listOfEmittedResult[1] is ScreenState.Error)
            job.cancel()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
