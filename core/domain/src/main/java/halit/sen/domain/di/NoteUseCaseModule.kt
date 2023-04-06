package halit.sen.domain.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import halit.sen.domain.usecase.note.*

@Module
@InstallIn(ViewModelComponent::class)
abstract class NoteUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetNoteUseCase(getNoteUseCaseImpl: GetNoteUseCaseImpl): GetNoteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAddNoteUseCase(addNoteUseCaseImpl: AddNoteUseCaseImpl): AddNoteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteNoteUseCase(deleteNoteUseCaseImpl: DeleteNoteUseCaseImpl): DeleteNoteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateNoteUseCase(updateNoteUseCaseImpl: UpdateNoteUseCaseImpl): UpdateNoteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllNoteUseCase(getAllNotesUseCaseImpl: GetAllNotesUseCaseImpl): GetAllNotesUseCase
}