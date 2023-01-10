package halit.sen.postpone.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDateFromTimeStamp(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("EEE, d MMMM yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}