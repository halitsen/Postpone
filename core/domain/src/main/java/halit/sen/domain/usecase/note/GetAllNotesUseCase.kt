package halit.sen.domain.usecase.note

import halit.sen.domain.entity.NoteEntity
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface GetAllNotesUseCase {
    operator fun invoke(): Flow<ResponseState<List<NoteEntity>>>
}