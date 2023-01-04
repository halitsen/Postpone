package com.example.postpone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.postpone.home.TodoScreen
import com.example.postpone.model.Note
import com.example.postpone.model.Todo
import com.example.postpone.navigation.PostponeNavHost
import com.example.postpone.screen.NoteScreen
import com.example.postpone.ui.theme.PostponeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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