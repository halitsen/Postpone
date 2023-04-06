package halit.sen.domain.usecase.note

import halit.sen.data.dto.Note
import halit.sen.data.repository.NoteRepository
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.mapper.PostponeBaseMapper
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: PostponeBaseMapper<Note, NoteEntity>
): GetNoteUseCase {
    override operator fun invoke(id: String): Flow<ResponseState<NoteEntity>> = channelFlow {
        send(ResponseState.Loading)
        repository.getNote(id).collectLatest { response ->
            when(response){
                is ResponseState.Error -> {
                    send(response)
                }
                is ResponseState.Success -> {
                    send(ResponseState.Success(mapper.map(response.data)))
                }
                else -> Unit
            }
        }
    }
}