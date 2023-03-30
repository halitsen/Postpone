package halit.sen.postpone.common

import androidx.annotation.StringRes

sealed class ScreenState<out T : Any> {
    object Loading : ScreenState<Nothing>()
    data class Error(@StringRes val message: Int) : ScreenState<Nothing>()
    data class Success<out T : Any>(val uiData: T) : ScreenState<T>()
}