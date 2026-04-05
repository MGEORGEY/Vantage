package ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import service.LocationService

@Composable
fun SettingsScreen(navController: NavController) {

    val context = LocalContext.current
    var trackingEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Settings")

        Spacer(modifier = Modifier.height(20.dp))
/*
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Background Location Tracking")

            Switch(
                checked = trackingEnabled,
                onCheckedChange = { isChecked ->
                    trackingEnabled = isChecked

                    val intent = Intent(context, LocationService::class.java)

                    if (isChecked) {
                        context.startService(intent)
                    } else {
                        context.stopService(intent)
                    }
                }
            )
        }
        */

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate("locations")
        }) {
            Text("View Saved Locations")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            navController.navigate("login") {
                popUpTo("dashboard") { inclusive = true }
            }
        }) {
            Text("Logout")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("App Version: 1.0")
        Text("Developer: Vantage Team")

    }
}