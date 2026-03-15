package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*
import data.AppDatabase
import data.LocationEntity


@Composable
fun MapScreen(navController: NavController) {

    val context = LocalContext.current

    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vantage-db"
        ).build()
    }

    val locationDao = db.locationDao()

    var locations by remember { mutableStateOf(listOf<LocationEntity>()) }

    LaunchedEffect(Unit) {
        locations = locationDao.getAllLocations()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        if (locations.isEmpty()) {
            Text(
                text = "No locations found",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            val firstLocation = locations.first()
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(
                    LatLng(firstLocation.latitude, firstLocation.longitude), 10f
                )
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                locations.forEach { location ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(location.latitude, location.longitude)
                        ),
                        title = location.name,
                        snippet = location.description
                    )
                }
            }
        }

        // Floating Action Button to add a new location
        FloatingActionButton(
            onClick = { navController.navigate("add_location") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .navigationBarsPadding()    // avoid system UI overlap
        ) {
            Text("+")
        }

    }
}