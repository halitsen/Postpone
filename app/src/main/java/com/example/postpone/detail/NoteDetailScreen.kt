package com.example.postpone.detail

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.postpone.home.TopBar
import com.example.postpone.model.Note
import com.example.postpone.model.Todo
import com.example.postpone.ui.theme.PostponeTheme

@Composable
fun NoteDetailScreen(
    note: Note?,
    onSaveNote: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit,
    onUpdateNote: (Note) -> Unit,
) {
    PostponeTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Scaffold(
                topBar = { TopBar() },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        val note = Note(noteTitle = "First note", description = "Hello this is first note")

                        val todo = Todo(description = "Hello this is first todo")
                        onSaveNote.invoke(note)
                    }) {
                        Icon(Icons.Filled.Check, "")
                    }
                }
            ) { padding ->
                Text(modifier = Modifier.padding(padding), text = note?.description?:"")
            }
        }
    }
}