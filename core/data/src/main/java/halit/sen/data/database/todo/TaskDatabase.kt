package halit.sen.data.database.todo

import androidx.room.Database
import androidx.room.RoomDatabase
import halit.sen.data.dto.Task

@Database(entities = [Task::class], version = 3, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDatabaseDao

}