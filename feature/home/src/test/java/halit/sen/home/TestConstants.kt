package halit.sen.home

import androidx.annotation.VisibleForTesting
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.entity.TaskEntity


@VisibleForTesting
val noteEntityList =
    listOf<NoteEntity>(
        NoteEntity("0"),
        NoteEntity("1"),
        NoteEntity("2")
    )

@VisibleForTesting
val taskEntityList =
    listOf<TaskEntity>(
        TaskEntity(0L, "This is test", true),
        TaskEntity(0L, "This is test2", true)
    )