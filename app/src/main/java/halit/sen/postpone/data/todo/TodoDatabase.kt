package halit.sen.postpone.data.todo

import androidx.room.Database
import androidx.room.RoomDatabase
import halit.sen.postpone.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDatabaseDao

}