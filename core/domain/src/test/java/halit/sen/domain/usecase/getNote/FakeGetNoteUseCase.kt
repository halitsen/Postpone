package halit.sen.domain.usecase.getNote

import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.noteEntity
import halit.sen.domain.usecase.note.GetNoteUseCase
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import java.io.IOException

class FakeGetNoteUseCase: GetNoteUseCase {

    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }

    override fun invoke(id: String): Flow<ResponseState<NoteEntity>> = flow {
        emit(ResponseState.Loading)
        if (showError) {
            emit(ResponseState.Error(IOException()))
        } else {
            emit(ResponseState.Success(noteEntity))
        }
    }
}