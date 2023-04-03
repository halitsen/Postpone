package halit.sen.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import halit.sen.data.dto.Note
import halit.sen.data.dto.Task
import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.mapper.*

@Module
@InstallIn(ViewModelComponent::class)
abstract class PostponeMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSingleNoteEntityMapper(noteEntityMapper: NoteEntityMapper): PostponeBaseMapper<Note, NoteEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindNoteListEntityMapper(noteListEntityMapper: NoteListEntityMapper): PostponeListMapper<Note, NoteEntity>

    @Binds
    @ViewModelScoped
    abstract fun bindTaskListEntityMapper(taskListEntityMapper: TaskListEntityMapper): PostponeListMapper<Task, TaskEntity>

}