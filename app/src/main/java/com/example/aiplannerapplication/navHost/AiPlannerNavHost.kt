package com.example.aiplannerapplication.navHost

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aiplannerapplication.ui.health.HealthTrackerRoute
import com.example.aiplannerapplication.ui.home.HomeScreen

sealed class Route(val name: String) {
    object HomeRoute: Route("Home")
    object HealthRoute: Route("Health")
    object ExpenseRoute: Route("Expense")
}


@Composable
fun AiPlannerNavHost() {

    val navController = rememberNavController()

    NavHost(navController, Route.HomeRoute.name) {
        composable(Route.HomeRoute.name) {
            HomeScreen{dest->
                navigateTo(navController, dest)
            }
        }
        composable(Route.HealthRoute.name) {
            HealthTrackerRoute()
        }
        composable(Route.ExpenseRoute.name) {
            HealthTrackerRoute()
        }
    }

}

fun navigateTo(navController: NavController, route: Route) {
    Log.d("###", "navigateTo: new route " + route.name)
    navController.navigate(route.name)
}