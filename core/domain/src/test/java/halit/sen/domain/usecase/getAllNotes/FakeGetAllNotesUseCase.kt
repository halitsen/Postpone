package halit.sen.domain.usecase.getAllNotes

import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.usecase.note.GetAllNotesUseCase
import halit.sen.postpone.common.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeGetAllNotesUseCase : GetAllNotesUseCase{
    private var showError = false

    fun updateShowError(showError: Boolean) {
        this.showError = showError
    }
    override fun invoke(): Flow<ResponseState<List<NoteEntity>>> = flow{
        emit(ResponseState.Loading)
        if(showError){
            emit(ResponseState.Error(IOException()))
        }else{
            emit(ResponseState.Success(listOf()))
        }
    }
}