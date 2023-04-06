package halit.sen.domain.mapper

import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.noteList
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class NoteListEntityMapperTest {

    private var noteListEntityMapper: NoteListEntityMapper = NoteListEntityMapper()

    private lateinit var noteListEntity: List<NoteEntity>

    @Before
    fun setup() {
        noteListEntity = noteListEntityMapper.map(noteList)
    }

    @Test
    fun assert_NoteListEntitySize_IreSameAfterMapped() {
        Assert.assertEquals(noteListEntity.size, noteListEntity.size)
    }

}