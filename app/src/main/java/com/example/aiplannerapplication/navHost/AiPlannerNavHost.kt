package com.example.aiplannerapplication.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aiplannerapplication.ui.home.HomeScreen

sealed class Route(val name: String) {
    object HomeRoute: Route("Home")
//    object ExpenseTracker: Route("Expense")
}


@Composable
fun AiPlannerNavHost() {

    val navController = rememberNavController()

    NavHost(navController, Route.HomeRoute.name) {
        composable(Route.HomeRoute.name) {
            HomeScreen()
        }
    }

}