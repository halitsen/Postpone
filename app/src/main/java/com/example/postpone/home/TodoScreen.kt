package com.example.postpone.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postpone.model.Todo

@Composable
fun TodoScreen(notes: List<Todo>) {
    LazyColumn(modifier = Modifier.background(color = Color.White)) {
        items(notes) { item ->
            TodoRow(todo = item)
        }
    }
}

@Preview
@Composable
fun TodoRow(
    modifier: Modifier = Modifier,
    todo: Todo = Todo(description = "This is todo"),
) {
    Surface(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier) {
                Checkbox(checked = todo.isDone, onCheckedChange = {})
                Text(
                    text = todo.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.secondary
                    ),
                    modifier = modifier.padding(top = 6.dp, bottom = 6.dp)
                )
            }
            Divider(
                Modifier
                    .height(0.5.dp)
                    .background(MaterialTheme.colors.secondary)
            )
        }
    }
}