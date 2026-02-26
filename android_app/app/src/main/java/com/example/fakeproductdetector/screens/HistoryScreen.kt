package com.example.fakeproductdetector.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fakeproductdetector.R
import androidx.compose.ui.text.font.FontWeight

data class ScanHistoryItem(
    val imageRes: Int,
    val prediction: String,
    val confidence: Int
)

@Composable
fun HistoryScreen(navController: NavController) {
    Text(
        text = "Previous Scan",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(16.dp))
    // First 2 Fake, rest Real
    val historyList = listOf(
        ScanHistoryItem(R.drawable.ic_launcher_foreground, "FAKE", 45),
        ScanHistoryItem(R.drawable.ic_launcher_foreground, "FAKE", 38),
        ScanHistoryItem(R.drawable.ic_launcher_foreground, "REAL", 82),
        ScanHistoryItem(R.drawable.ic_launcher_foreground, "REAL", 91),
        ScanHistoryItem(R.drawable.ic_launcher_foreground, "REAL", 76)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(historyList) { item ->
            HistoryCard(item)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun HistoryCard(item: ScanHistoryItem) {
    Spacer(modifier = Modifier.height(16.dp))
    Spacer(modifier = Modifier.height(16.dp))
    val badgeColor =
        if (item.prediction == "REAL") Color(0xFF4CAF50)
        else Color(0xFFF44336)

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = item.prediction,
                    fontSize = 18.sp,
                    color = badgeColor
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Confidence: ${item.confidence}%",
                    fontSize = 14.sp
                )
            }
        }
    }
}