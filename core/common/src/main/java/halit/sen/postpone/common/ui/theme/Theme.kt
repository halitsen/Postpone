package halit.sen.postpone.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkSurface,
    primaryVariant = DarkSurface,
    secondary = DarkTextColor,
    surface = DarkSurface,
    secondaryVariant = DarkOnSurface,
    background = DarkOnSurface,
    error = DarkRed,
    onError = OnErrorRed
)

private val LightColorPalette = lightColors(
    primary = DarkSurface,
    primaryVariant = DarkSurface,
    secondary = LightTextColor,
    surface = LightOnSurface,
    secondaryVariant = DarkOnSurface,
    background = LightOnSurface,
    error = Red,
    onError = OnErrorDarkRed
)

@Composable
fun PostponeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}