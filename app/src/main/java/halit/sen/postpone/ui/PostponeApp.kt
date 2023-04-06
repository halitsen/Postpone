package halit.sen.postpone.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import halit.sen.postpone.navigation.PostponeNavHost
import halit.sen.postpone.common.ui.theme.PostponeTheme

@Composable
fun PostponeApp() {
    PostponeTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            PostponeNavHost(navController)
        }
    }
}