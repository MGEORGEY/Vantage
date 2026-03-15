package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.must.vantage.DashboardScreen
import data.AppDatabase
import data.LocationEntity
import ui.*


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("dashboard")
                }
            )
        }

        composable("dashboard") {
            DashboardScreen(navController)
        }

        composable("profile") {
            ProfileScreen()
        }

        composable("data") {
            DataScreen()
        }

        composable("map") {
            MapScreen(navController)
        }

        composable("add_location") {
            AddLocationScreen()
        }

        composable("locations") {
            LocationListScreen()
        }

        composable("settings") {
            SettingsScreen(navController)
        }

    }
}