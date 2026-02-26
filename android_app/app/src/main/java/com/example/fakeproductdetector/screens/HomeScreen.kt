package com.example.fakeproductdetector.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.AccountCircle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet {


                Text(
                    text = "Fake Product Detector",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Welcome, User",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "user@email.com",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Divider()

                Divider()

                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("home")
                    },
                    icon = { Icon(Icons.Default.Home, null) }
                )

                NavigationDrawerItem(
                    label = { Text("Scan") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("scan")
                    },
                    icon = { Icon(Icons.Default.Search, null) }
                )

                NavigationDrawerItem(
                    label = { Text("History") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("history")
                    },
                    icon = { Icon(Icons.Default.History, null) }
                )

                NavigationDrawerItem(
                    label = { Text("About") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("about")
                    },
                    icon = { Icon(Icons.Default.Info, null) }
                )

                NavigationDrawerItem(
                    label = { Text("Contact") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("contact")
                    },
                    icon = { Icon(Icons.Default.Email, null) }
                )

                NavigationDrawerItem(
                    label = { Text("Logout") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("login")
                    },
                    icon = { Icon(Icons.Default.ExitToApp, null) }
                )
            }
        }
    ) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {
                        Text("Fake Product Detector")
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }

        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = "Welcome",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                FeatureCard(
                    "Scan Product",
                    "Detect fake products using AI",
                    Icons.Default.Search
                ) {
                    navController.navigate("scan")
                }

                FeatureCard(
                    "Scan History",
                    "View previous scan results",
                    Icons.Default.History
                ) {
                    navController.navigate("history")
                }

                FeatureCard(
                    "AI Protection",
                    "Advanced CNN security detection",
                    Icons.Default.Security
                ) {}

                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            "Why use this app?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("• Detect fake electronics instantly")
                        Text("• AI powered detection")
                        Text("• Secure and reliable")
                        Text("• Easy to use interface")
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    desc: String,
    icon: ImageVector,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(icon, null, modifier = Modifier.size(32.dp))

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(desc)
            }
        }
    }
}