package com.must.vantage

import com.must.vantage.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import ui.DataScreen
import ui.ProfileScreen

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContent {
           // DashboardScreen()
        //}
    }
}

@Composable
fun DashboardScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Vantage Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

            Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "A demo app from Meru University students, Jan-Apr 2026",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { navController.navigate("profile") }) {
            Text("Profile")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { navController.navigate("map") }) {
            Text("Activity Map")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { navController.navigate("locations") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text("Data Management")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { navController.navigate("settings") }) {
            Text("Settings", color = Color.Blue)
        }

    }
}