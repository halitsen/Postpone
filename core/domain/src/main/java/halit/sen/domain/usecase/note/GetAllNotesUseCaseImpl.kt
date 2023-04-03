package halit.sen.domain.usecase.note

import halit.sen.data.dto.Note
import halit.sen.data.repository.NoteRepository
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.mapper.PostponeBaseMapper
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: PostponeBaseMapper<List<Note>, List<NoteEntity>>
): GetAllNotesUseCase{

    override fun invoke(): Flow<ResponseState<List<NoteEntity>>> = flow {
        emit(ResponseState.Loading)
        when(val response = repository.getAllNotes()){
            is ResponseState.Error ->{
                emit(response)
            }
            is ResponseState.Success ->{
                emit(ResponseState.Success(mapper.map(response.data)))
            }
        }
    }
}