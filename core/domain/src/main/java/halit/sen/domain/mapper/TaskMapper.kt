package halit.sen.domain.mapper

import halit.sen.data.dto.Task
import halit.sen.domain.entity.TaskEntity
import javax.inject.Inject

class TaskMapper @Inject constructor(): PostponeBaseMapper<TaskEntity, Task> {
    override fun map(input: TaskEntity): Task {
        return Task(
            id = input.id,
            description = input.description,
            isDone = input.isDone
        )
    }
}