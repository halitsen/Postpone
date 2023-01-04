package com.example.postpone.navigation

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
    private const val idArg = "id"
    val routeWithArgs = "$route/{$idArg}"
    val arguments = listOf(
        navArgument(idArg) {
            type = NavType.StringType
        }
    )
}