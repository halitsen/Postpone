package halit.sen.domain.mapper

import halit.sen.domain.entity.TaskEntity
import halit.sen.domain.taskList
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class TaskListEntityMapperTest {

    private var noteListEntityMapper: TaskListEntityMapper = TaskListEntityMapper()

    private lateinit var taskListEntity: List<TaskEntity>

    @Before
    fun setup() {
        taskListEntity = noteListEntityMapper.map(taskList)
    }

    @Test
    fun assert_NoteListEntitySize_IreSameAfterMapped() {
        Assert.assertEquals(taskListEntity.size, taskListEntity.size)
    }
}