package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import data.AppDatabase
import data.LocationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddLocationScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "vantage-db"
    ).build()
    val locationDao = db.locationDao()

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Add Location")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Location Name") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text("Latitude") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = longitude,
            onValueChange = { longitude = it },
            label = { Text("Longitude") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            // Save location to database
            if(name.isNotBlank() && description.isNotBlank() &&
                latitude.isNotBlank() && longitude.isNotBlank()) {

                val location = LocationEntity(
                    name = name,
                    description = description,
                    latitude = latitude.toDouble(),
                    longitude = longitude.toDouble()
                )

                // Launch in a coroutine to avoid blocking UI
                CoroutineScope(Dispatchers.IO).launch {
                    locationDao.insertLocation(location)
                }

                name = ""
                description = ""
                latitude = ""
                longitude = ""
            }
        }) {
            Text("Save Location")
        }

    }
}
