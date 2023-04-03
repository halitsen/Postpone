package halit.sen.domain.mapper

import halit.sen.data.dto.Task
import halit.sen.domain.entity.TaskEntity
import javax.inject.Inject

class TaskListEntityMapper @Inject constructor(): PostponeListMapper<Task, TaskEntity> {
    override fun map(input: List<Task>): List<TaskEntity> {
        return input.map {
            TaskEntity(
                id = it.id,
                description = it.description,
                isDone = it.isDone
            )
        }
    }
}