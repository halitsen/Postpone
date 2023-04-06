package halit.sen.domain.usecase.note

import halit.sen.domain.entity.NoteEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface UpdateNoteUseCase {
    operator fun invoke(noteEntity: NoteEntity): Flow<ResponseState<Boolean>>
}