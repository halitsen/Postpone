package halit.sen.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import halit.sen.postpone.common.ScreenState
import halit.sen.postpone.common.R as coreRes

@Composable
fun NoteDetailRoute(onBackPressed: () -> Unit, viewModel: NoteDetailViewModel = hiltViewModel()) {

    when (val noteUiState = viewModel.noteDetailUiState.collectAsState().value) {
        is ScreenState.Error -> {
            Toast
                .makeText(
                    LocalContext.current.applicationContext,
                    coreRes.string.error,
                    Toast.LENGTH_SHORT
                )
                .show()
        }
        is ScreenState.Success -> {
            NoteDetailScreen(
                note = noteUiState.uiData,
                onSaveNote = { viewModel.saveNote(it) },
                onDeleteNoteClicked = { viewModel.onDeleteNoteClicked(noteUiState.uiData) },
                onBackPressed = onBackPressed
            )
        }
        else -> Unit
    }
}