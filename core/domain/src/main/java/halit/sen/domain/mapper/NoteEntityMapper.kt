package halit.sen.domain.mapper

import halit.sen.data.dto.Note
import halit.sen.domain.entity.NoteEntity
import javax.inject.Inject

class NoteEntityMapper  @Inject constructor(): PostponeBaseMapper<Note, NoteEntity> {
    override fun map(input: Note): NoteEntity {
        return NoteEntity(
            id = input.id.toString(),
            title = input.noteTitle,
            description = input.description,
            lastEdit = input.noteLastEdit.toString(),
        )
    }
}