package halit.sen.domain.mapper

import halit.sen.domain.entity.NoteEntity
import halit.sen.domain.note
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class NoteEntityMapperTest {
    private val noteEntityMapper: NoteEntityMapper = NoteEntityMapper()

    private lateinit var noteEntity: NoteEntity

    @Before
    fun setup() {
        noteEntity = noteEntityMapper.map(note)
    }

    @Test
    fun assert_NoteTitle_IsSameAfterMapped() {
        assertEquals(noteEntity.title, note.noteTitle)
    }

    @Test
    fun assert_NoteDescription_IsSameAfterMapped() {
        assertEquals(noteEntity.description, note.description)
    }
}