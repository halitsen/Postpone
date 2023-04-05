package halit.sen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

import halit.sen.domain.entity.NoteEntity
import halit.sen.home.note.NoteViewModel
import halit.sen.home.task.TaskViewModel
import halit.sen.home.ui.HomeScreen
import halit.sen.postpone.common.ScreenState

@Composable
fun HomeRoute(
    onNoteClicked: (NoteEntity) -> Unit,
    onFabClicked: () -> Unit,
    noteViewModel: NoteViewModel = hiltViewModel(),
    taskViewModel: TaskViewModel = hiltViewModel()
) {
    val noteScreenState by noteViewModel.noteUiState.collectAsState(initial = ScreenState.Loading)
    val todoScreenState by taskViewModel.taskUiState.collectAsState(initial = ScreenState.Loading)

    HomeScreen(
        noteScreenState = noteScreenState,
        todoScreenState = todoScreenState,
        onAddTodo = { taskViewModel.addTask(it) },
        onNoteClicked = { onNoteClicked.invoke(it) },
        onFabClicked = { onFabClicked() },
        onNoteDeleteClicked = { noteViewModel.deleteNote(it) },
        onUpdateTodoClicked = { note, isDone -> taskViewModel.updateTask(note, isDone) },
        onDeleteTodo = { taskViewModel.deleteTask(it) }
    )
}