package halit.sen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.usecase.note.AddNoteUseCase
import halit.sen.postpone.common.R as coreRes
import halit.sen.domain.usecase.note.DeleteNoteUseCase
import halit.sen.domain.usecase.note.GetNoteUseCase
import halit.sen.domain.usecase.note.UpdateNoteUseCase
import halit.sen.postpone.common.ResponseState
import halit.sen.postpone.common.ScreenState
import halit.sen.postpone.common.getDateFromTimeStamp
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _note = MutableStateFlow<NoteEntity?>(null)
    val note = _note.asStateFlow()

    private val _noteDetailUiState =
        MutableStateFlow<ScreenState<NoteEntity>>(value = ScreenState.Loading)
    val noteDetailUiState: StateFlow<ScreenState<NoteEntity>> get() = _noteDetailUiState.asStateFlow()


    val description = MutableStateFlow(savedStateHandle["description"] ?: "").asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    init {
        getNote()
        _date.value =
            getDateFromTimeStamp(if (_note.value == null) System.currentTimeMillis() else _note.value!!.lastEdit.toLong())
    }

    private fun getNote() {
        viewModelScope.launch {
            savedStateHandle.get<String>("id")?.let { id ->
                getNoteUseCase(id).collectLatest { response ->
                    when (response) {
                        is ResponseState.Loading -> {
                            _noteDetailUiState.emit(ScreenState.Loading)
                        }
                        is ResponseState.Error -> {
                            _noteDetailUiState.emit(ScreenState.Error(coreRes.string.error))
                        }
                        is ResponseState.Success -> {
                            _noteDetailUiState.emit(ScreenState.Success(response.data))
                        }
                    }
                }
            }.apply {
                _noteDetailUiState.emit(ScreenState.Success(NoteEntity("0","","")))
            }
        }
    }

    fun saveNote(note: NoteEntity) {
        viewModelScope.launch {
            if (description.value.trim() == "") {
                if (note.description.trim().isEmpty().not() && note.description != "")
                    addNoteUseCase(note).collect()
            } else {
                if (note.description.trim().isEmpty().not() && note.description != "")
                    updateNoteUseCase(note).collect()
            }
        }
    }

    fun onDeleteNoteClicked(note: NoteEntity) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }
}