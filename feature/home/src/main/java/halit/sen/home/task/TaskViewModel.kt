package halit.sen.home.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.usecase.task.AddTaskUseCase
import halit.sen.domain.usecase.task.DeleteTaskUseCase
import halit.sen.domain.usecase.task.GetAllTasksUseCase
import halit.sen.domain.usecase.task.UpdateTaskUseCase
import halit.sen.postpone.common.R
import halit.sen.postpone.common.ResponseState
import halit.sen.postpone.common.ScreenState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
): ViewModel() {

    private val _taskUiState =
        MutableStateFlow<ScreenState<List<TaskEntity>>>(value = ScreenState.Loading)
    val taskUiState: StateFlow<ScreenState<List<TaskEntity>>> get() = _taskUiState.asStateFlow()

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTasksUseCase().collectLatest {
                when (it) {
                    is ResponseState.Error -> {
                        _taskUiState.emit(ScreenState.Error(R.string.error))
                    }
                    ResponseState.Loading -> {
                        _taskUiState.emit(ScreenState.Loading)
                    }
                    is ResponseState.Success -> {
                        if (it.data.isEmpty()) {
                            _taskUiState.emit(ScreenState.Error(R.string.empty_note))
                        } else {
                            _taskUiState.emit(ScreenState.Success(it.data))
                        }
                    }
                }
            }
        }
    }

    fun addTask(taskEntity: TaskEntity) {
        viewModelScope.launch {
            addTaskUseCase(taskEntity).collect()
        }
    }

    fun updateTask(taskEntity: TaskEntity, isDone: Boolean) {
        taskEntity.isDone = isDone
        viewModelScope.launch {
            updateTaskUseCase(taskEntity).collect()
        }
    }

    fun deleteTask(taskEntity: TaskEntity) {
        viewModelScope.launch {
            deleteTaskUseCase(taskEntity).collect()
        }
    }
}