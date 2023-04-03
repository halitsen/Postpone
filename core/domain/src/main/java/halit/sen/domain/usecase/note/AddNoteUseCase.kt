package halit.sen.domain.usecase.note

import halit.sen.data.dto.Note
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow


interface AddNoteUseCase {
    operator fun invoke(note: Note): Flow<ResponseState<Boolean>>
}