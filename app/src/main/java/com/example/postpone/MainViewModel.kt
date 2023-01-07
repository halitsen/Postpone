package com.example.postpone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postpone.model.Note
import com.example.postpone.model.Todo
import com.example.postpone.repository.NoteRepository
import com.example.postpone.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
    val todoList = _todoList.asStateFlow()

    init {
        getAllNotes()
        getAllTodos()
    }

    private fun getAllTodos(){
        viewModelScope.launch{
            todoRepository.getAllTodos().distinctUntilChanged().collect {
                if (it.isEmpty().not()) {
                    _todoList.value = it
                }
            }
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch{
            noteRepository.getAllNotes().distinctUntilChanged().collect {
                if (it.isEmpty().not()) {
                    _noteList.value = it
                }
            }
        }
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch {
            todoRepository.addTodo(todo)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}