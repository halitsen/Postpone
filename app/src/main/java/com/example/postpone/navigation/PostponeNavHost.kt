package com.example.postpone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.postpone.detail.NoteDetailRoute
import com.example.postpone.home.HomeRoute

@Composable
fun PostponeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
        composable(route = Home.route) {
            HomeRoute(onNoteClicked = {
                val route = "${NoteDetail.route}/${it.id}"
                navController.navigate(route = route)
            })
        }

        composable(
            route = NoteDetail.routeWithArgs,
            arguments = NoteDetail.arguments
        ) {
            NoteDetailRoute(onBackPressed = {
                navController.navigateUp()
            })
        }
    }
}