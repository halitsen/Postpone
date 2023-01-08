package com.example.postpone.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.postpone.MainViewModel
import com.example.postpone.model.Note
import com.example.postpone.model.Todo

@Composable
fun HomeRoute(
    onNoteClicked: (Note) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val notes = viewModel.noteList.collectAsState().value
    val todos = viewModel.todoList.collectAsState().value

    HomeScreen(
        notes = notes,
        todos = todos,
        onAddTodo = { viewModel.addTodo(it) },
        onNoteClicked = { onNoteClicked.invoke(it) },
        onNoteDeleteClicked = { viewModel.deleteNote(it) },
        onUpdateTodoClicked = { note, isDone -> viewModel.onUpdateTodo(note, isDone) },
        onDeleteTodo = {viewModel.onDeleteTodo(it)}
    )
}