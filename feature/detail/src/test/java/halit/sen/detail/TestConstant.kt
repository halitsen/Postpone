package halit.sen.detail

import androidx.annotation.VisibleForTesting
import halit.sen.domain.entity.NoteEntity

@VisibleForTesting
val noteEntity =
    NoteEntity(
        id = "1",
        title = "note title",
        description = "note Description",
    )

@VisibleForTesting
const val noteEntityPathId = "1"

@VisibleForTesting
const val idArg = "id"
