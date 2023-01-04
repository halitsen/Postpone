package com.example.postpone.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postpone.model.Note
import com.example.postpone.ui.theme.PostponeTheme

@Composable
fun NoteDetailScreen(
    note: Note?,
    date: String,
    onSaveNote: (Note) -> Unit,
    onDeleteNote: () -> Unit,
    onUpdateNote: (Note) -> Unit,
    onBackPressed: () -> Unit,
    onTextChange: (String) -> Unit
) {
    PostponeTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            var text by rememberSaveable { mutableStateOf(note?.description ?: "") }
            Scaffold(
                topBar = { DetailTopBar(onBackPressed, onDeleteNote) },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        onSaveNote.invoke(Note(description = text))
                    }) {
                        Icon(Icons.Filled.Check, "")
                    }
                }
            ) { padding ->
                Column(modifier = Modifier.padding(2.dp)) {
                    Text(
                        text = date,
                        modifier = Modifier.padding(start = 14.dp, top = 16.dp),
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    Spacer(modifier = Modifier.background(color = Color.Transparent))
                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                            onTextChange(it)
                        },
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.surface
                        ),
                        singleLine = false,
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.surface,
                            disabledTextColor = MaterialTheme.colors.surface,
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text("Note It")
                        }
                    )

                }

            }
        }
    }
}

@Composable
fun DetailTopBar(onBackPressed: () -> Unit, onDeleteNote: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Postpone", fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }, modifier = Modifier.width(48.dp)) {
                Icon(Icons.Outlined.ArrowBack, "")
            }
        },
        actions = {
            IconButton(onClick = { onDeleteNote() }) {
                Icon(Icons.Outlined.Delete, "", tint = Color.White)
            }
        }
    )
}