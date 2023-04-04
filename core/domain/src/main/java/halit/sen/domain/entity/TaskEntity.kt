package halit.sen.domain.entity

data class TaskEntity(
    val id: Long = 0L,
    val description:String = "",
    var isDone: Boolean = false
)