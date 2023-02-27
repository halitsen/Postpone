package halit.sen.postpone.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import halit.sen.postpone.TabItem
import halit.sen.postpone.detail.DeleteNoteAlertDialog
import halit.sen.postpone.model.Note
import halit.sen.postpone.utils.getDateFromTimeStamp

@Composable
fun NoteScreen(
    notes: List<Note>,
    onNoteClicked: (Note) -> Unit,
    onNoteDeleteClicked: (Note) -> Unit
) {
    if (notes.isEmpty()) {
        EmptyStateView(tabItem = TabItem.Note)
    } else {
        LazyColumn(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .padding(top = 6.dp)
                .animateContentSize().testTag("note_list")
        ) {
            items(notes){item ->
                NoteRow(
                    note = item,
                    onNoteClicked = onNoteClicked,
                    onDeleteNoteClicked = onNoteDeleteClicked
                )
            }
        }
    }
}

@Preview
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note = Note(description = "Note Description", noteTitle = "Note Title"),
    onNoteClicked: (Note) -> Unit = {},
    onDeleteNoteClicked: (Note) -> Unit = {}
) {
    var interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
            .fillMaxWidth()
    ) {
        val openDialog = remember { mutableStateOf(false) }
        if (openDialog.value) {
            DeleteNoteAlertDialog(
                item = TabItem.Note,
                onCancel = { openDialog.value = false },
                onConfirm = {
                    openDialog.value = false
                    onDeleteNoteClicked(note)
                })
        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 12.dp), verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onNoteClicked.invoke(note)
                }) {
                Column(
                    modifier = modifier.weight(1f, true)
                ) {
                    Text(
                        text = getDateFromTimeStamp(note.noteLastEdit),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.secondary.copy(0.7f)
                        )
                    )
                    Text(
                        text = note.description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.secondary
                        ),
                        modifier = modifier.padding(top = 6.dp, bottom = 6.dp)
                    )
                }
                Box(modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    openDialog.value = true
                }) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "",
                        tint = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .padding(12.dp)
                    )
                }
            }
            Divider(
                Modifier
                    .height(0.5.dp)
                    .background(MaterialTheme.colors.secondary)
            )
        }

    }
}