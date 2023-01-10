package halit.sen.postpone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import halit.sen.postpone.model.Note
import halit.sen.postpone.model.Todo
import halit.sen.postpone.repository.NoteRepository
import halit.sen.postpone.repository.TodoRepository
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
                    _todoList.value = it
            }
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch{
            noteRepository.getAllNotes().distinctUntilChanged().collect {
                    _noteList.value = it
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

    fun onUpdateTodo(todo: Todo, isDone: Boolean){
        viewModelScope.launch {
            todo.isDone = isDone
            todoRepository.updateTodo(todo)
        }
    }

    fun onDeleteTodo(todo: Todo){
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }
}