package com.example.postpone.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.postpone.TabItem
import com.example.postpone.model.Note
import com.example.postpone.model.Todo
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    notes: List<Note>,
    todos: List<Todo>,
    onAddTodo: (Todo) -> Unit,
    onNoteClicked: (Note) -> Unit,
    onNoteDeleteClicked: (Note) -> Unit
) {
    val tabs = listOf(TabItem.Note, TabItem.Todo)
    val pagerState = rememberPagerState()
    val openDialog = remember { mutableStateOf(false) }

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
            }) {
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
                onNoteDeleteClicked = onNoteDeleteClicked
            )
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Postpone", fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
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
    onNoteDeleteClicked: (Note) -> Unit
) {
    HorizontalPager(state = pagerState, count = tabs.size, userScrollEnabled = false, verticalAlignment = Alignment.Top, modifier = Modifier.background(MaterialTheme.colors.background)) { page ->
        when (page) {
            0 -> {
                NoteScreen(notes, onNoteClicked = onNoteClicked, onNoteDeleteClicked = onNoteDeleteClicked)
            }
            1 -> {
                TodoScreen(todos)
            }
        }
    }
}