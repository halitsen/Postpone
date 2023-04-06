package halit.sen.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "description")
    var description: String="",
    @ColumnInfo(name = "is_done")
    var isDone: Boolean = false,
    @ColumnInfo(name = "task_created_at")
    var createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name= "importance")
    val importance: String = "high"
)