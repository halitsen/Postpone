package halit.sen.home.note

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
import halit.sen.postpone.common.ResponseState
import halit.sen.postpone.common.ScreenState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import halit.sen.postpone.common.R as coreRes

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _noteUiState =
        MutableStateFlow<ScreenState<List<NoteEntity>>>(value = ScreenState.Loading)
    val noteUiState: StateFlow<ScreenState<List<NoteEntity>>> get() = _noteUiState.asStateFlow()

    init {
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

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            deleteNoteUseCase(noteEntity).collect()
        }

    }
}