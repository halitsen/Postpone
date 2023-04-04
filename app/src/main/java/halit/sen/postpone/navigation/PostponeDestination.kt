package halit.sen.postpone.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface PostponeDestination {
    val route: String
}

object Home : PostponeDestination {
    override val route: String
        get() = "home"
}

object NoteDetail : PostponeDestination {
    override val route: String
        get() = "detail"
    private const val idArg: String = "id"
    private const val descriptionArg = "description"
    val routeWithArgs = "$route/{$idArg}/{$descriptionArg}"
    val arguments = listOf(
        navArgument(idArg) {
            type = NavType.StringType
        },
        navArgument(descriptionArg){
            type = NavType.StringType
        }
    )
}