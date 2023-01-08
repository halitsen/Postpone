package com.example.postpone.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NoteDetailRoute(onBackPressed: () -> Unit, viewModel: NoteDetailViewModel = hiltViewModel()) {
    val note = viewModel.note.collectAsState().value
    val date = viewModel.date.collectAsState().value
    val description = viewModel.description.collectAsState().value?:""
    NoteDetailScreen(
        note = note,
        date = date,
        description,
        onSaveNote = { viewModel.saveNote(it) },
        onDeleteNoteClicked = { note?.let { viewModel.onDeleteNoteClicked(it) } },
        onBackPressed = onBackPressed
    )
}