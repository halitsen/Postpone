package halit.sen.domain.mapper

import halit.sen.data.dto.Task
import halit.sen.domain.taskEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TaskMapperTest {

    private val taskMapper = TaskMapper()
    private lateinit var task: Task

    @Before
    fun setup() {
        task = taskMapper.map(taskEntity)
    }

    @Test
    fun assert_TaskDescription_IsSameAfterMapped() {
        Assert.assertEquals(taskEntity.description, task.description)
    }

    @Test
    fun assert_TaskIsDone_IsSameAfterMapped() {
        Assert.assertEquals(taskEntity.isDone, task.isDone)
    }
}