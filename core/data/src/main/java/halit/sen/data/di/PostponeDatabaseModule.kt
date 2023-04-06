package halit.sen.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import halit.sen.data.database.note.NoteDatabase
import halit.sen.data.database.note.NoteDatabaseDao
import halit.sen.data.database.todo.TaskDatabase
import halit.sen.data.database.todo.TaskDatabaseDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PostponeDatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context, NoteDatabase::class.java, "notes_db",
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDatabaseDao = taskDatabase.taskDao()

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase =
        Room.databaseBuilder(
            context, TaskDatabase::class.java, "task_db",
        )
            .fallbackToDestructiveMigration()
            .build()
}