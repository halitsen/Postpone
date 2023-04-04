package halit.sen.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import halit.sen.data.repository.NoteRepository
import halit.sen.data.repository.NoteRepositoryImpl
import halit.sen.data.repository.TaskRepository
import halit.sen.data.repository.TaskRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object PostponeRepositoryModule {


    @Provides
    @ViewModelScoped
    fun bindNoteRepository(noteRepositoryImpl: NoteRepositoryImpl):  NoteRepository{
        return noteRepositoryImpl
    }

    @Provides
    @ViewModelScoped
    fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository{
        return taskRepositoryImpl
    }
}