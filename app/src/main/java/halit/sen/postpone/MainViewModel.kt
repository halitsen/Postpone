package halit.sen.postpone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import halit.sen.postpone.common.R as coreRes
import halit.sen.postpone.common.ScreenState
import halit.sen.postpone.model.Note
import halit.sen.postpone.model.Todo
import halit.sen.postpone.repository.NoteRepository
import halit.sen.postpone.repository.TodoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _noteScreenState =
        MutableStateFlow<ScreenState<List<Note>>>(value = ScreenState.Loading)
    val noteScreenState: StateFlow<ScreenState<List<Note>>> get() = _noteScreenState.asStateFlow()

    private val _todoScreenState =
        MutableStateFlow<ScreenState<List<Todo>>>(value = ScreenState.Loading)
    val todoScreenState: StateFlow<ScreenState<List<Todo>>> get() = _todoScreenState.asStateFlow()

    init {
        getAllNotes()
        getAllTodos()
    }

    private fun getAllTodos(){
        viewModelScope.launch{
            todoRepository.getAllTodos().collectLatest { todoList ->
                if(todoList.isNotEmpty()){
                _todoScreenState.emit(ScreenState.Success(todoList))
                }else{
                    _todoScreenState.emit(ScreenState.Error(coreRes.string.empty_todo))
                }
            }
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch{
            noteRepository.getAllNotes().collectLatest { noteList ->
                if(noteList.isNotEmpty()){
                    _noteScreenState.emit(ScreenState.Success(noteList))
                }else{
                    _noteScreenState.emit(ScreenState.Error(coreRes.string.empty_note))
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