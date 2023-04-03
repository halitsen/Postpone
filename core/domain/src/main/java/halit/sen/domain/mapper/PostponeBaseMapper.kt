package halit.sen.domain.mapper

interface PostponeBaseMapper<I, O> {
    fun map(input: I): O
}