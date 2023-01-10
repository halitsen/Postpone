package halit.sen.postpone.data.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import halit.sen.postpone.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDatabaseDao {

    @Query("SELECT * from todo_table")
    fun getAllTodos(): Flow<List<Todo>>

    @Query("SELECT * from todo_table where id = :id")
    suspend fun getTodo(id: String): Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todo: Todo)

    @Query("DELETE from todo_table")
    suspend fun deleteAllTodos()

    @Delete()
    suspend fun deleteTodo(todo: Todo)

}