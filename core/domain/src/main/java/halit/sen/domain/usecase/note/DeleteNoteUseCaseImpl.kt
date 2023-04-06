package halit.sen.domain.usecase.note

import halit.sen.data.dto.Note
import halit.sen.data.repository.NoteRepository
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.mapper.PostponeBaseMapper
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository,
    private val  mapper: PostponeBaseMapper<NoteEntity, Note>
): DeleteNoteUseCase {
    override operator fun invoke(noteEntity: NoteEntity): Flow<ResponseState<Boolean>> = flow {
        emit(ResponseState.Loading)

        when (val response = repository.deleteNote(mapper.map(noteEntity))) {
            is ResponseState.Error -> {
                emit(response)
            }
            is ResponseState.Success -> {
                emit(ResponseState.Success(response.data))
            }
            else -> Unit
        }
    }
}