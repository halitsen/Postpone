package com.example.postpone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.postpone.navigation.PostponeNavHost
import com.example.postpone.ui.theme.PostponeTheme
import dagger.hilt.android.AndroidEntryPoint

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