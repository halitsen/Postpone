package halit.sen.postpone.home

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import halit.sen.postpone.BuildConfig
import halit.sen.postpone.R
import halit.sen.postpone.TabItem
import halit.sen.postpone.model.Note
import halit.sen.postpone.model.Todo
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    notes: List<Note>,
    todos: List<Todo>,
    onAddTodo: (Todo) -> Unit,
    onNoteClicked: (Note) -> Unit,
    onNoteDeleteClicked: (Note) -> Unit,
    onUpdateTodoClicked: (Todo, Boolean) -> Unit,
    onDeleteTodo: (Todo) -> Unit
) {
    val tabs = listOf(TabItem.Note, TabItem.Todo)
    val pagerState = rememberPagerState()
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (openDialog.value) {
        TodoAddDialog(onCancel = { openDialog.value = false }, onConfirm = {
            onAddTodo(it)
            openDialog.value = false
        })
    }
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val note = Note(noteTitle = " ", description = " ")
                if (pagerState.currentPage == 0) {
                    onNoteClicked.invoke(note)
                } else {
                    openDialog.value = true
                }
            }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(
                tabs = tabs,
                pagerState = pagerState,
                notes = notes,
                todos = todos,
                onNoteClicked = onNoteClicked,
                onNoteDeleteClicked = onNoteDeleteClicked,
                onUpdateTodoClicked = onUpdateTodoClicked,
                onDeleteTodo = onDeleteTodo
            )
        }
    }
}

@Composable
fun TopBar() {
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = "Postpone", fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        actions = {
            IconButton(onClick = {
                redirectToShareIntent(context)
            }) {
                Icon(
                    painterResource(id = R.drawable.ic_share),
                    "",
                    tint = Color.White
                )
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.primary,
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
fun TabsContent(
    tabs: List<TabItem>,
    pagerState: PagerState,
    notes: List<Note>,
    todos: List<Todo>,
    onNoteClicked: (Note) -> Unit,
    onNoteDeleteClicked: (Note) -> Unit,
    onUpdateTodoClicked: (Todo, Boolean) -> Unit,
    onDeleteTodo: (Todo) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        count = tabs.size,
        userScrollEnabled = false,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) { page ->
        when (page) {
            0 -> {
                NoteScreen(
                    notes,
                    onNoteClicked = onNoteClicked,
                    onNoteDeleteClicked = onNoteDeleteClicked
                )
            }
            1 -> {
                TodoScreen(
                    todos = todos,
                    onUpdateTodo = onUpdateTodoClicked,
                    onDeleteTodo = onDeleteTodo
                )
            }
        }
    }
}

private fun redirectToShareIntent(context: Context) {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(
        Intent.EXTRA_TEXT,
        "Hey!!! Check out this cool app here: https://play.google.com/store/apps/details?id=halit.sen.postpone"
    )
    intent.type = "text/plain"
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val chooserIntent = Intent.createChooser(intent, "Share")
    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(chooserIntent)

}