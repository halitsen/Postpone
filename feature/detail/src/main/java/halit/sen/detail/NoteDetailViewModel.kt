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
    private val _noteDetailUiState =
        MutableStateFlow<ScreenState<NoteEntity>>(value = ScreenState.Loading)
    val noteDetailUiState: StateFlow<ScreenState<NoteEntity>> get() = _noteDetailUiState.asStateFlow()

    private val id: String? = savedStateHandle.get<String>("id")

    init {
        getNote()
    }

    private fun getNote() {
        viewModelScope.launch {
            if (id == null) {
                _noteDetailUiState.emit(
                    ScreenState.Success(
                        NoteEntity(
                            "0",
                            "",
                            "",
                            getDateFromTimeStamp(System.currentTimeMillis())
                        )
                    )
                )
            } else {
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
            }
        }
    }

    fun saveNote(note: String) {
        viewModelScope.launch {
            if ((_noteDetailUiState.value as ScreenState.Success).uiData.description == "") {
                if (note.trim().isEmpty().not() && note != "")
                    addNoteUseCase(NoteEntity(id = "", title = "", description = note)).collect()
            } else {
                if (note.trim().isEmpty().not() && note != "")
                    updateNoteUseCase(
                        NoteEntity(
                            id = id ?: "",
                            title = "",
                            description = note
                        )
                    ).collect()
            }
        }
    }

    fun onDeleteNoteClicked(note: NoteEntity) {
        viewModelScope.launch {
            deleteNoteUseCase(note).collect()
        }
    }
}