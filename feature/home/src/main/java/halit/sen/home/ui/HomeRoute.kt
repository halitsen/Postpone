package halit.sen.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

import halit.sen.domain.entity.NoteEntity
import halit.sen.home.HomeViewModel
import halit.sen.postpone.common.ScreenState

@Composable
fun HomeRoute(
    onNoteClicked: (NoteEntity) -> Unit,
    onFabClicked: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val todoScreenState by viewModel.taskUiState.collectAsState(initial = ScreenState.Loading)
    val noteScreenState by viewModel.noteUiState.collectAsState(initial = ScreenState.Loading)


    HomeScreen(
        noteScreenState = noteScreenState,
        todoScreenState = todoScreenState,
        onAddTodo = { viewModel.addTask(it) },
        onNoteClicked = { onNoteClicked.invoke(it) },
        onFabClicked = { onFabClicked() },
        onNoteDeleteClicked = { viewModel.deleteNote(it) },
        onUpdateTodoClicked = { note, isDone -> viewModel.updateTask(note, isDone) },
        onDeleteTodo = { viewModel.deleteTask(it) }
    )
}