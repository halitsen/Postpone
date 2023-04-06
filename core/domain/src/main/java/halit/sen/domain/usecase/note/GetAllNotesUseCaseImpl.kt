package halit.sen.domain.usecase.note

import halit.sen.data.dto.Note
import halit.sen.data.repository.NoteRepository
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.mapper.PostponeListMapper
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: PostponeListMapper<Note, NoteEntity>
): GetAllNotesUseCase{

    override fun invoke(): Flow<ResponseState<List<NoteEntity>>> = channelFlow {
        send(ResponseState.Loading)

        repository.getAllNotes().collectLatest { response ->
            when(response){
                is ResponseState.Error ->{
                    send(response)
                }
                is ResponseState.Success ->{
                    send(ResponseState.Success(mapper.map(response.data)))
                }
                else -> Unit
            }
        }
    }
}