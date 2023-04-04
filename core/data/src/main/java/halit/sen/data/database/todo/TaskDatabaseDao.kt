package halit.sen.data.database.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import halit.sen.data.dto.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDatabaseDao {

    @Query("SELECT * from task_table")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: Task)

    @Delete()
    suspend fun deleteTask(task: Task)

}