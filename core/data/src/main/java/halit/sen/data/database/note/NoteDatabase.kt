package halit.sen.data.database.note

import androidx.room.Database
import androidx.room.RoomDatabase
import halit.sen.data.dto.Note

@Database(entities = [Note::class], version = 3, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}