package halit.sen.postpone.common


sealed class TabItem(val icon: Int, val title: String, val description: String) {
    object Note : TabItem(R.drawable.ic_note, "Note", "note")
    object Task : TabItem(R.drawable.ic_check, "Task", "task")
}