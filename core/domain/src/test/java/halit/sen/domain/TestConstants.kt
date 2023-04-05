package halit.sen.domain

import androidx.annotation.VisibleForTesting
import halit.sen.domain.entity.NoteEntity

@VisibleForTesting
const val noteId = "1"

@VisibleForTesting
val noteEntity =
    NoteEntity(
        id = "1",
        description = "test entity",
        title = "test entity"
    )