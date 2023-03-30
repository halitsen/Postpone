package halit.sen.postpone.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import halit.sen.postpone.MainViewModel
import halit.sen.postpone.common.ScreenState
import halit.sen.postpone.model.Note

@Composable
fun HomeRoute(
    onNoteClicked: (Note) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val todoScreenState by viewModel.todoScreenState.collectAsState(initial = ScreenState.Loading)
    val noteScreenState by viewModel.noteScreenState.collectAsState(initial = ScreenState.Loading)


    HomeScreen(
        noteScreenState = noteScreenState,
        todoScreenState = todoScreenState,
        onAddTodo = { viewModel.addTodo(it) },
        onNoteClicked = { onNoteClicked.invoke(it) },
        onNoteDeleteClicked = { viewModel.deleteNote(it) },
        onUpdateTodoClicked = { note, isDone -> viewModel.onUpdateTodo(note, isDone) },
        onDeleteTodo = {viewModel.onDeleteTodo(it)}
    )
}