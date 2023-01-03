package com.example.postpone.di

import android.content.Context
import androidx.room.Room
import com.example.postpone.data.note.NoteDatabase
import com.example.postpone.data.note.NoteDatabaseDao
import com.example.postpone.data.todo.TodoDatabase
import com.example.postpone.data.todo.TodoDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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