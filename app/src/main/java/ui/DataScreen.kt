package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

data class Record(val id: Int, var title: String, var description: String)

@Composable
fun DataScreen() {

    var records by remember { mutableStateOf(
        mutableListOf(
            Record(1, "Sample 1", "Description 1"),
            Record(2, "Sample 2", "Description 2")
        )
    )}

    var newTitle by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Data Management",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = newTitle,
            onValueChange = { newTitle = it },
            label = { Text("Title") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = newDescription,
            onValueChange = { newDescription = it },
            label = { Text("Description") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            if(newTitle.isNotBlank() && newDescription.isNotBlank()) {
                records.add(Record(records.size + 1, newTitle, newDescription))
                newTitle = ""
                newDescription = ""
            }
        }) {
            Text("Add Record")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(records) { record ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = record.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = record.description)
                    }
                }
            }
        }
    }
}
