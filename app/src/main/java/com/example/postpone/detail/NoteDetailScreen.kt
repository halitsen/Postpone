package com.example.postpone.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postpone.model.Note
import com.example.postpone.ui.theme.PostponeTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteDetailScreen(
    note: Note?,
    date: String,
    noteDescription: String?,
    onSaveNote: (Note) -> Unit,
    onDeleteNoteClicked: (Note?) -> Unit,
    onBackPressed: () -> Unit
) {
    PostponeTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            var text by rememberSaveable { mutableStateOf(noteDescription?.trim() ?: "") }
            val openDialog = remember { mutableStateOf(false) }
            if (openDialog.value) {
                DeleteNoteAlertDialog(onCancel = { openDialog.value = false }, onConfirm = {
                    openDialog.value = false
                    onDeleteNoteClicked(note)
                    onBackPressed()
                })
            }
            Scaffold(
                topBar = {
                    DetailTopBar(onBackPressed) {
                        openDialog.value = true
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        onSaveNote.invoke(Note(description = text))
                        onBackPressed()
                    }) {
                        Icon(Icons.Filled.Check, "")
                    }
                }
            ) { _ ->
                Column(modifier = Modifier.padding(2.dp)) {
                    Text(
                        text = date,
                        modifier = Modifier.padding(start = 14.dp, top = 16.dp),
                        color = MaterialTheme.colors.secondary
                    )
                    Spacer(modifier = Modifier.background(color = Color.Transparent))
                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.secondary
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        singleLine = false,
                        enabled = true,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.secondary,
                            cursorColor = MaterialTheme.colors.secondary,
                            disabledTextColor = MaterialTheme.colors.primary,
                            backgroundColor = MaterialTheme.colors.background,
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
fun DetailTopBar(onBackPressed: () -> Unit, onDeleteNoteClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Postpone", fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }, modifier = Modifier.width(48.dp)) {
                Icon(Icons.Outlined.ArrowBack, "")
            }
        },
        actions = {
            IconButton(onClick = { onDeleteNoteClicked() }) {
                Icon(Icons.Outlined.Delete, "", tint = Color.White)
            }
        }
    )
}