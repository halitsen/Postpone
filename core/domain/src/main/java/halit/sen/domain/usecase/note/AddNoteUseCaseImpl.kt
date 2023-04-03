package halit.sen.domain.usecase.note

import halit.sen.data.dto.Note
import halit.sen.data.repository.NoteRepository
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : AddNoteUseCase {
    override operator fun invoke(note: Note): Flow<ResponseState<Boolean>> = flow {
        emit(ResponseState.Loading)

        when (val response = repository.addNote(note)) {
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