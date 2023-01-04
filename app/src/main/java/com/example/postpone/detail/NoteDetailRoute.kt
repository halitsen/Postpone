package com.example.postpone.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NoteDetailRoute(viewModel: NoteDetailViewModel = hiltViewModel()) {
    val note = viewModel.note.collectAsState().value
    NoteDetailScreen(
        note = note,
        onSaveNote = {viewModel.saveNote(it)},
        onDeleteNote = {},
        onUpdateNote = {}
    )
}