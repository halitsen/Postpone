package halit.sen.postpone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "note_title")
    var noteTitle: String = "",
    @ColumnInfo(name = "note_description")
    var description: String="",
    @ColumnInfo(name = "note_last_edit")
    var noteLastEdit: Long = System.currentTimeMillis()
)