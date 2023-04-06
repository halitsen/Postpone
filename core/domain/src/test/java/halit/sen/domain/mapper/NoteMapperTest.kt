package halit.sen.domain.mapper

import halit.sen.data.dto.Note
import halit.sen.domain.noteEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NoteMapperTest {
    private val noteMapper = NoteMapper()
    private lateinit var note: Note

    @Before
    fun setup() {
        note = noteMapper.map(noteEntity)
    }

    @Test
    fun assert_NoteTitle_IsSameAfterMapped() {
        Assert.assertEquals(noteEntity.title, note.noteTitle)
    }

    @Test
    fun assert_NoteDescription_IsSameAfterMapped() {
        Assert.assertEquals(noteEntity.description, note.description)
    }
}