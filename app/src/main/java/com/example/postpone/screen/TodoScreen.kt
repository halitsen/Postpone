package com.example.postpone.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.postpone.model.Todo

@Composable
fun TodoScreen(notes: List<Todo>) {
    LazyColumn(modifier = Modifier.background(color = Color.White)) {
        items(notes) { item ->
            TodoRow(todo = item, onUpdateTodo = {

            })
        }
    }
}

@Composable
fun TodoRow(
    modifier: Modifier = Modifier,
    todo: Todo,
    onUpdateTodo: (Todo) -> Unit
) {

    Surface(
        modifier = modifier
            .padding(top = 6.dp, bottom = 6.dp, start = 6.dp, end = 6.dp)
            .clip(
                shape = RoundedCornerShape(
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .fillMaxWidth(),
        color = Color(0xFF1F2544)
    ) {
        Row {
            Checkbox(checked = todo.isDone, onCheckedChange = {})
            Text(text = todo.description)
        }
    }
}