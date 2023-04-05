package halit.sen.domain

import androidx.annotation.VisibleForTesting
import halit.sen.data.dto.Note
import halit.sen.data.dto.Task
import halit.sen.domain.entity.NoteEntity

@VisibleForTesting
const val noteId = "1"

@VisibleForTesting
val noteEntity =
    NoteEntity(
        id = "1",
        description = "test entity description",
        title = "test entity"
    )

@VisibleForTesting
val note =
    Note(
        id = 0L,
        description = "test entity",
        noteTitle = "test entity"
    )

val noteList = listOf<Note>(
    Note(
        id = 0L,
        description = "test entity",
        noteTitle = "test entity"
    ),
    Note(
        id = 1L,
        description = "test entity2",
        noteTitle = "test entity2"
    )
)

val taskList = listOf<Task>(
    Task(id = 0L, description = "test task",  isDone = false),
    Task(id = 1L, description = "test task2",  isDone = true),
    Task(id = 2L, description = "test task2",  isDone = false)
)