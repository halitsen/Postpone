package halit.sen.postpone

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import halit.sen.postpone.navigation.PostponeNavHost
import halit.sen.postpone.ui.theme.PostponeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
                PostponeApp()
        }
    }
}

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