package halit.sen.postpone.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import halit.sen.postpone.TabItem
import halit.sen.postpone.detail.DeleteNoteAlertDialog
import halit.sen.postpone.model.Todo

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoScreen(
    todos: List<Todo>,
    onUpdateTodo: (Todo, Boolean) -> Unit,
    onDeleteTodo: (Todo) -> Unit
) {
    if (todos.isEmpty()) {
        EmptyStateView(TabItem.Todo)
    } else {
        Surface {
            val openDialog = remember { mutableStateOf(false) }
            val todo = remember { mutableStateOf(Todo()) }
            if (openDialog.value) {
                DeleteNoteAlertDialog(
                    item = TabItem.Todo,
                    onCancel = { openDialog.value = false },
                    onConfirm = {
                        openDialog.value = false
                        onDeleteTodo(todo.value)
                    })
            }

            LazyColumn(modifier = Modifier.background(color = Color.White)) {
                itemsIndexed(items = todos, key = { _, item ->
                    item.id
                }) { _, item ->
                    val state = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart) {
                                openDialog.value = true
                                todo.value = item
                            }
                            false
                        },

                        )
                    SwipeToDismiss(
                        state = state,
                        background = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .background(color = MaterialTheme.colors.secondary)
                                    .padding(end = 12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    "delete",
                                    tint = MaterialTheme.colors.onError,
                                    modifier = Modifier.align(
                                        Alignment.CenterEnd
                                    )
                                )
                            }
                        },
                        dismissContent = {
                            TodoRow(todo = item, onUpdateTodo = { todo, isDone ->
                                onUpdateTodo(todo, isDone)
                            })
                        },
                        directions = setOf(DismissDirection.EndToStart),
                    )
                }
            }
        }
    }
}

@Composable
fun TodoRow(
    modifier: Modifier = Modifier,
    todo: Todo,
    onUpdateTodo: (Todo, Boolean) -> Unit
) {
    val isCheckedState = remember { mutableStateOf(todo.isDone) }
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
                Checkbox(
                    checked = isCheckedState.value,
                    onCheckedChange = {
                        isCheckedState.value = it
                        onUpdateTodo(todo, it)
                    },
                )
                Text(
                    text = todo.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.secondary
                    ),
                    modifier = modifier.padding(top = 6.dp, bottom = 6.dp),
                    textDecoration = if (isCheckedState.value) TextDecoration.LineThrough else TextDecoration.None
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