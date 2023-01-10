package halit.sen.postpone.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import halit.sen.postpone.data.note.NoteDatabase
import halit.sen.postpone.data.note.NoteDatabaseDao
import halit.sen.postpone.data.todo.TodoDatabase
import halit.sen.postpone.data.todo.TodoDatabaseDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

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
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDatabaseDao = todoDatabase.todoDao()

    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase =
        Room.databaseBuilder(
            context, TodoDatabase::class.java, "todo_db",
        )
            .fallbackToDestructiveMigration()
            .build()
}