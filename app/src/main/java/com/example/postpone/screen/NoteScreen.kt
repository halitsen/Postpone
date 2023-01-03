package com.example.postpone.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.postpone.model.Note

@Composable
fun NoteScreen(notes: List<Note>) {
    LazyColumn {
        items(notes) { item ->
            NoteRow(note = item, onNoteClicked = {
            })
            Divider(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {

    Surface(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topEnd = 36.dp,
                    bottomStart = 36.dp
                )
            )
            .fillMaxWidth(),
        color = Color(0xFF1F2544)
    ) {

        Column(
            modifier = modifier
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .clickable {
                    onNoteClicked.invoke(note)
                }, horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.noteTitle,
                style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.caption,
                color = Color.White
            )
        }

    }

}