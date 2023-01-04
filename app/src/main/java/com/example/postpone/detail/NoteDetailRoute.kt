package com.example.postpone.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NoteDetailRoute(onBackPressed: () -> Unit, viewModel: NoteDetailViewModel = hiltViewModel()) {
    val note = viewModel.note.collectAsState().value
    val date = viewModel.date.collectAsState().value
    NoteDetailScreen(
        note = note,
        date = date,
        onSaveNote = { viewModel.saveNote(it) },
        onDeleteNote = { note?.let { viewModel.deleteNote(it) } },
        onUpdateNote = {},
        onBackPressed = onBackPressed,
        onTextChange = {viewModel.onTextChange(it)}
    )
}