package halit.sen.home

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.usecase.note.DeleteNoteUseCase
import halit.sen.domain.usecase.note.GetAllNotesUseCase
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
import halit.sen.postpone.common.R as coreRes

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _noteUiState =
        MutableStateFlow<ScreenState<List<NoteEntity>>>(value = ScreenState.Loading)
    val noteUiState: StateFlow<ScreenState<List<NoteEntity>>> get() = _noteUiState.asStateFlow()

    private val _taskUiState =
        MutableStateFlow<ScreenState<List<TaskEntity>>>(value = ScreenState.Loading)
    val taskUiState: StateFlow<ScreenState<List<TaskEntity>>> get() = _taskUiState.asStateFlow()


    init {
        getAllTasks()
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase().collectLatest {
                when (it) {
                    is ResponseState.Error -> {
                        _noteUiState.emit(ScreenState.Error(coreRes.string.error))
                    }
                    ResponseState.Loading -> {
                        _noteUiState.emit(ScreenState.Loading)
                    }
                    is ResponseState.Success -> {
                        if (it.data.isEmpty()) {
                            _noteUiState.emit(ScreenState.Error(coreRes.string.empty_note))
                        } else {
                            _noteUiState.emit(ScreenState.Success(it.data))
                        }
                    }
                }
            }
        }
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTasksUseCase().collectLatest {
                when (it) {
                    is ResponseState.Error -> {
                        _taskUiState.emit(ScreenState.Error(coreRes.string.error))
                    }
                    ResponseState.Loading -> {
                        _taskUiState.emit(ScreenState.Loading)
                    }
                    is ResponseState.Success -> {
                        if (it.data.isEmpty()) {
                            _taskUiState.emit(ScreenState.Error(coreRes.string.empty_note))
                        } else {
                            _taskUiState.emit(ScreenState.Success(it.data))
                        }
                    }
                }
            }
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            deleteNoteUseCase(noteEntity).collect()
        }

    }

    fun addTask(taskEntity: TaskEntity) {
        viewModelScope.launch {
            addTaskUseCase(taskEntity).collectLatest {
                when(it){
                    is ResponseState.Success -> {
                        Log.i("SUCCESSS",it.data.toString())
                    }
                    is ResponseState.Loading -> {
                        Log.i("loading"," ")
                    }
                    is ResponseState.Error -> {
                        Log.i("ERRORR", it.exception.message.toString())
                    }
                }
            }
        }

    }

    fun updateTask(taskEntity: TaskEntity, isDone: Boolean) {
        taskEntity.isDone = isDone
        viewModelScope.launch {
            updateTaskUseCase(taskEntity)
        }
    }

    fun deleteTask(taskEntity: TaskEntity) {
        viewModelScope.launch {
            deleteTaskUseCase(taskEntity)
        }
    }
}