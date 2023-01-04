package com.example.postpone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.postpone.model.Note
import com.example.postpone.model.Todo
import com.example.postpone.screen.NoteScreen
import com.example.postpone.screen.TodoScreen
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
            PostponeTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel: MainViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp(mainViewModel: MainViewModel) {
    val notes = mainViewModel.noteList.collectAsState().value
    val todos = mainViewModel.todoList.collectAsState().value
    MainScreen(
        notes = notes,
        todos = todos,
        onAddNote = { mainViewModel.addNote(it) },
        onAddTodo = { mainViewModel.addTodo(it) },
        onRemoveNote = { }
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    notes: List<Note>,
    todos: List<Todo>,
    onAddNote: (Note) -> Unit,
    onAddTodo: (Todo) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    val tabs = listOf(TabItem.Note, TabItem.Todo)
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val note = Note(noteTitle = "First note", description = "Hello this is first note")
                //onAddNote.invoke(note)
                val todo = Todo(description = "Hello this is first todo")
                onAddTodo.invoke(todo)
            }) {
                Icon(Icons.Filled.Add,"")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState, notes = notes, todos = todos)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.White
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, notes: List<Note>, todos: List<Todo>) {
    HorizontalPager(state = pagerState, count = tabs.size, userScrollEnabled = false) { page ->
        when (page) {
            0 -> {
                NoteScreen(notes)
            }
            1 -> {
                TodoScreen(todos)
            }
        }
    }
}