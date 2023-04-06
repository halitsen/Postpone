package halit.sen.home

import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.usecase.task.AddTaskUseCase
import halit.sen.domain.usecase.task.DeleteTaskUseCase
import halit.sen.domain.usecase.task.GetAllTasksUseCase
import halit.sen.domain.usecase.task.UpdateTaskUseCase
import halit.sen.home.task.TaskViewModel
import halit.sen.postpone.common.R
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

class TaskViewModelTest {

    @Mock
    private lateinit var getAllTaskUseCase: GetAllTasksUseCase

    @Mock
    private lateinit var updateTaskUseCase: UpdateTaskUseCase

    @Mock
    private lateinit var addTaskUseCase: AddTaskUseCase

    @Mock
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    private lateinit var viewModel: TaskViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = TaskViewModel(getAllTaskUseCase, addTaskUseCase, updateTaskUseCase,deleteTaskUseCase)
    }

    @Test
    fun getAllTasksState_WhenUseCaseReturnsLoading_isLoading() {
        runBlocking {
            val resultList = listOf(ResponseState.Loading)
            whenever(getAllTaskUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf<ScreenState<List<TaskEntity>>>(ScreenState.Loading)

            val job = launch {
                viewModel.taskUiState.toList(listOfEmittedResult)
            }
            verify(getAllTaskUseCase).invoke()
            assert(listOfEmittedResult.first() is ScreenState.Loading)
            job.cancel()
        }
    }

    @Test
    fun getAllTasksState_WhenUseCaseReturnsLoadingAndSuccess_isLoadingAndSuccessSequentially() {
        runBlocking {
            val resultList = listOf(
                ResponseState.Loading, ResponseState.Success(taskEntityList)
            )
            whenever(getAllTaskUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf(ScreenState.Loading, ScreenState.Success(taskEntityList))
            val job = launch {
                viewModel.taskUiState.toList(listOfEmittedResult)
            }
            verify(getAllTaskUseCase).invoke()
            assert(listOfEmittedResult[1] is ScreenState.Success)
            job.cancel()
        }
    }

    @Test
    fun getAllTasksState_WhenUseCaseReturnsLoadingAndError_isLoadingAndErrorSequentially() {
        runBlocking {
            val resultList = listOf(
                ResponseState.Loading, ResponseState.Error(IOException())
            )
            whenever(getAllTaskUseCase.invoke()).thenReturn(resultList.asFlow())

            val listOfEmittedResult =
                mutableListOf<ScreenState<List<TaskEntity>>>(
                    ScreenState.Loading,
                    ScreenState.Error(R.string.error)
                )
            val job = launch {
                viewModel.taskUiState.toList(listOfEmittedResult)
            }
            verify(getAllTaskUseCase).invoke()
            assert(listOfEmittedResult[1] is ScreenState.Error)
            job.cancel()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


}