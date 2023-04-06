package halit.sen.domain.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import halit.sen.data.dto.Task
import halit.sen.data.repository.TaskRepository
import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.mapper.PostponeBaseMapper
import halit.sen.domain.mapper.PostponeListMapper
import halit.sen.domain.usecase.task.*

@Module
@InstallIn(ViewModelComponent::class)
abstract class TaskUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAddTaskUseCase(addTaskUseCase: AddTaskUseCaseImpl): AddTaskUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteTaskUseCase(deleteTaskUseCaseImpl: DeleteTaskUseCaseImpl): DeleteTaskUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateTaskUseCase(updateTaskUseCaseImpl: UpdateTaskUseCaseImpl): UpdateTaskUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllTasksUseCase(getAllTasksUseCaseImpl: GetAllTasksUseCaseImpl): GetAllTasksUseCase
}