package halit.sen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import halit.sen.postpone.common.ScreenState

@Composable
fun NoteDetailRoute(onBackPressed: () -> Unit, viewModel: NoteDetailViewModel = hiltViewModel()) {

    when(val noteUiState = viewModel.noteDetailUiState.collectAsState().value){
        is ScreenState.Loading -> {
            //todo loading state
        }
        is ScreenState.Error -> {
            //todo error state
        }
        is ScreenState.Success -> {
            NoteDetailScreen(
                note = noteUiState.uiData,
                onSaveNote = { viewModel.saveNote(it) },
                onDeleteNoteClicked = { viewModel.onDeleteNoteClicked(noteUiState.uiData)},
                onBackPressed = onBackPressed
            )
        }
    }
}