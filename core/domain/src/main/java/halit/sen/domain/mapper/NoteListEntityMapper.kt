package halit.sen.domain.mapper

import halit.sen.data.dto.Note
import halit.sen.domain.entity.NoteEntity
import javax.inject.Inject

class NoteListEntityMapper @Inject constructor(): PostponeListMapper<Note, NoteEntity> {
    override fun map(input: List<Note>): List<NoteEntity> {
        return input.map {
            NoteEntity(
                id = it.id.toString(),
                title = it.noteTitle,
                description = it.description,
                lastEdit = it.noteLastEdit.toString()
            )
        }
    }
}