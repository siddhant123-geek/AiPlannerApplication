package com.example.aiplannerapplication.navHost

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.aiplannerapplication.SplashScreen
import com.example.aiplannerapplication.ui.health.HealthTrackerRoute
import com.example.aiplannerapplication.ui.home.HomeScreen
import com.example.aiplannerapplication.ui.login.LoginScreenRoute
import com.example.aiplannerapplication.viewmodel.LoginViewModel

sealed class Route(val name: String) {
    object SplashScreenRoute: Route("splash")
    object LandingScreenRoute: Route("landing")
    object HomeRoute: Route("Home")
    object HealthRoute: Route("Health")
    object ExpenseRoute: Route("Expense")
}


@Composable
fun AiPlannerNavHost(viewModel: LoginViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    val isUserLoggedIn = viewModel.loggedInState.collectAsState()

    NavHost(navController, Route.SplashScreenRoute.name) {
        composable(Route.SplashScreenRoute.name) {
            SplashScreen(
                onTimeOut = {
                    if (isUserLoggedIn.value) {
                        Log.d("###", "AiPlannerNavHost: coming to loggin in Splash")
                        navController.navigate(Route.HomeRoute.name) {
                            popUpTo(Route.HomeRoute.name) { inclusive = true }
                        }
                    } else {
                        Log.d("###", "AiPlannerNavHost: coming to logged out in Splash")
                        navController.navigate(Route.LandingScreenRoute.name) {
                            popUpTo(Route.LandingScreenRoute.name) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Route.LandingScreenRoute.name) {
            LoginScreenRoute({user ->
                Log.d("###", "AiPlannerNavHost: user email after successful login " + user.email)
                navigateTo(navController, Route.HomeRoute)
            })
        }
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