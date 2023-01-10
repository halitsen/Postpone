package halit.sen.postpone.data.note

import androidx.room.Database
import androidx.room.RoomDatabase
import halit.sen.postpone.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}