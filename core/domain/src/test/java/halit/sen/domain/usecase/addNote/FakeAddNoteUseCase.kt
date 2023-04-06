package halit.sen.domain.usecase.addNote

import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.usecase.note.AddNoteUseCase
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeAddNoteUseCase: AddNoteUseCase {
    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }
    override fun invoke(noteEntity: NoteEntity): Flow<ResponseState<Boolean>> = flow{
        emit(ResponseState.Loading)
        if(showError){
            emit(ResponseState.Error(IOException()))
        }else{
            emit(ResponseState.Success(true))
        }
    }
}