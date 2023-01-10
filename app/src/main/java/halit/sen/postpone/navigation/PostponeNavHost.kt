package halit.sen.postpone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import halit.sen.postpone.detail.NoteDetailRoute
import halit.sen.postpone.home.HomeRoute

@Composable
fun PostponeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
        composable(route = Home.route) {
            HomeRoute(onNoteClicked = {
                val route = "${NoteDetail.route}/${it.id}/${it.description}"
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