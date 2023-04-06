package halit.sen.domain.mapper

import halit.sen.data.dto.Note
import halit.sen.domain.entity.NoteEntity
import javax.inject.Inject

class NoteMapper @Inject constructor() : PostponeBaseMapper<NoteEntity, Note> {
    override fun map(input: NoteEntity): Note {
        return if (input.id == "") {
            Note(
                noteTitle = input.title,
                description = input.description,
                noteLastEdit = System.currentTimeMillis()
            )
        } else {
            //for updating room db
            Note(
                id = input.id.toLong(),
                noteTitle = input.title,
                description = input.description,
                noteLastEdit = System.currentTimeMillis()
            )
        }

    }
}