package halit.sen.postpone.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import halit.sen.postpone.model.Note
import halit.sen.postpone.repository.NoteRepository
import halit.sen.postpone.utils.getDateFromTimeStamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _note = MutableStateFlow<Note?>(null)
    val note = _note.asStateFlow()

    val description = MutableStateFlow(savedStateHandle["description"] ?: "").asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    init {
        getNote()
        _date.value =
            getDateFromTimeStamp(if (_note.value == null) System.currentTimeMillis() else _note.value!!.noteLastEdit)
    }

    private fun getNote() {
        viewModelScope.launch {
            savedStateHandle.get<String>("id")?.let {
                noteRepository.getNote(it).collect { note ->
                    _note.value = note
                }
            }
        }
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            if (description.value.trim() == "") {
                if (note.description.trim().isEmpty().not() && note.description != "")
                    noteRepository.addNote(note)
            } else {
                if (note.description.trim().isEmpty().not() && note.description != "")
                    _note.value?.description = note.description
                    noteRepository.updateNote(_note.value?:note)
            }
        }
    }

    fun onDeleteNoteClicked(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}