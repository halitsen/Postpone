package halit.sen.domain.mapper

import halit.sen.data.dto.Note
import halit.sen.domain.entity.NoteEntity
import javax.inject.Inject

class NoteMapper @Inject constructor(): PostponeBaseMapper<NoteEntity, Note> {
    override fun map(input: NoteEntity): Note {
        //todo silme önemli updateler çalışmayabilir
        return Note(
            id = input.id.toLong(),
            noteTitle = input.title,
            description = input.description,
            noteLastEdit = input.lastEdit.toLong()
        )
    }
}