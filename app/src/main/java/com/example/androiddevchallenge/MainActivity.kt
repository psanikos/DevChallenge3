/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.grey_900

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            MyTheme {

                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "Welcome",
        builder = {
            composable("Welcome") { Welcome(navController = navController) }
            composable("Login") { Login(navController = navController) }
            composable("Main") { MainScreen(navController = navController) }
        }
    )
}

@Composable
fun Welcome(navController: NavController) {

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.dark_welcome else R.drawable.light_welcome),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.dark_logo else R.drawable.light_logo),
                contentDescription = "",
            )

            Surface(
                modifier = Modifier.padding(top = 32.dp, bottom = 8.dp),
                color = Color.Transparent
            ) {
                Button(
                    onClick = { /*TODO*/ }, shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (isSystemInDarkTheme()) Color.White else Color(red = 45, green = 45, blue = 45)
                    ),
                    modifier = Modifier
                        .height(72.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        "SIGN UP", style = MaterialTheme.typography.button,
                        color = if (isSystemInDarkTheme()) grey_900 else Color.White
                    )
                }
            }

            Button(
                onClick = {
                    navController.navigate("Login")
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSystemInDarkTheme()) Color(223, 164, 166) else Color(126, 87, 88)

                ),
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    "LOG IN", style = MaterialTheme.typography.button,
                    color = if (isSystemInDarkTheme()) grey_900 else Color.White
                )
            }
        }
    }
}

@Composable
fun Login(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val textfieldModifier = Modifier
        .height(56.dp)
        .fillMaxWidth()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.dark_login else R.drawable.light_login),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text("LOG IN", style = MaterialTheme.typography.h1, color = MaterialTheme.colors.onBackground)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier.padding(bottom = 8.dp)) {
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = textfieldModifier, textStyle = MaterialTheme.typography.body1,
                    placeholder = { Text("Email address", style = MaterialTheme.typography.body1) },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        placeholderColor = MaterialTheme.colors.onSurface,
                        backgroundColor = MaterialTheme.colors.surface,
                    )
                )
            }
            Box(modifier = Modifier.padding(bottom = 8.dp)) {
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = textfieldModifier, textStyle = MaterialTheme.typography.body1,
                    placeholder = {
                        Text(
                            "Password",
                            style = MaterialTheme.typography.body1,

                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        placeholderColor = MaterialTheme.colors.onSurface,
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                )
            }
            Button(
                onClick = {
                    navController.navigate("Main")
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSystemInDarkTheme()) Color.White else Color(red = 45, green = 45, blue = 45)
                ),
                modifier = Modifier
                    .height(72.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    "LOG IN", style = MaterialTheme.typography.button,
                    color = if (isSystemInDarkTheme()) grey_900 else Color.White
                )
            }
            Box(modifier = Modifier.height(32.dp), contentAlignment = Alignment.BottomCenter) {
                Row() {
                    Text("Don't have an account? ", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onBackground)
                    Text(
                        "Sign up ", style = MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.Underline), color = MaterialTheme.colors.onBackground,

                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    var searchInput by remember { mutableStateOf("") }
    val textfieldModifier = Modifier
        .height(56.dp)
        .fillMaxWidth()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,

        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 8.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.Spa, contentDescription = "", tint = MaterialTheme.colors.onBackground.copy(alpha = if (selectedTab == 0) 1.0f else 0.7f),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    label = {
                        Text(
                            "HOME", style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onBackground.copy(alpha = if (selectedTab == 0) 1.0f else 0.7f)
                        )
                    },
                    selected = selectedTab == 0,
                    onClick = {
//                    selectedTab = 0
//                    navController.navigate("Home") {
//
//                        popUpTo = navController.graph.startDestination
//                        launchSingleTop = true
//                    }
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.AccountCircle, contentDescription = "", tint = MaterialTheme.colors.onBackground.copy(alpha = if (selectedTab == 1) 1.0f else 0.7f),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    label = {
                        Text(
                            "PROFILE", style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onBackground.copy(alpha = if (selectedTab == 1) 1.0f else 0.7f)
                        )
                    },
                    selected = selectedTab == 1,
                    onClick = {
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            Surface(
                color = MaterialTheme.colors.primary, shape = CircleShape,
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
        ) {
            item {
                Box(modifier = Modifier.padding(bottom = 8.dp)) {
                    TextField(
                        value = searchInput,
                        onValueChange = {
                            searchInput = it
                        },
                        modifier = textfieldModifier, textStyle = MaterialTheme.typography.body1,
                        placeholder = { Text("Search", style = MaterialTheme.typography.body1) },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.8f),
                            placeholderColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.8f),
                            backgroundColor = MaterialTheme.colors.surface,
                        ),
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Search, contentDescription = "",
                                tint = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.8f)
                            )
                        }
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        "FAVORITE COLLECTIONS", style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            item {
                LazyRow(
                    content = {
                        item {
                            Column() {
                                Collections(imageId = R.drawable.p7, imageTitle = "Short mantras")
                                Collections(imageId = R.drawable.p11, imageTitle = "Nature meditations")
                            }
                        }
                        item {
                            Column() {
                                Collections(imageId = R.drawable.p8, imageTitle = "Stress and relief")
                                Collections(imageId = R.drawable.p13, imageTitle = "Self-massage")
                            }
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        "ALIGN YOUR BODY", style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            item {
                LazyRow(
                    content = {

                        item {
                            Body(imageId = R.drawable.p2, imageTitle = "Inversions")
                        }
                        item { Body(imageId = R.drawable.p1, imageTitle = "Quick yoga") }
                        item { Body(imageId = R.drawable.p16, imageTitle = "Stretching") }
                        item { Body(imageId = R.drawable.p5, imageTitle = "Tabatha") }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        "ALIGN YOUR MIND", style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            item {
                LazyRow(
                    content = {

                        item {
                            Body(imageId = R.drawable.p4, imageTitle = "Meditate")
                        }
                        item { Body(imageId = R.drawable.p6, imageTitle = "Relax") }
                        item { Body(imageId = R.drawable.p14, imageTitle = "Recover") }
                        item { Body(imageId = R.drawable.p15, imageTitle = "Calm") }
                    }
                )
            }
        }
    }
}

@Composable
fun Collections(imageId: Int, imageTitle: String) {
    Surface(
        shape = MaterialTheme.shapes.small, color = MaterialTheme.colors.surface,
        modifier = Modifier.padding(bottom = 8.dp, end = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .width(192.dp)
                .height(56.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .width(56.dp)

            ) {
                Image(
                    painter = painterResource(id = imageId), contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                imageTitle, style = MaterialTheme.typography.h3, color = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.8f),
                modifier = Modifier.padding(horizontal = 16.dp), maxLines = 2
            )
        }
    }
}

@Composable
fun Body(imageId: Int, imageTitle: String) {
    Column(Modifier.padding(end = 8.dp)) {
        Box(
            modifier = Modifier
                .height(88.dp)
                .width(88.dp)
                .clip(CircleShape)

        ) {
            Image(
                painter = painterResource(id = imageId), contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .height(24.dp)
                .width(88.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                imageTitle, style = MaterialTheme.typography.h3, color = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.8f),
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    val navController = rememberNavController()
    MyTheme(darkTheme = false) {
        MainScreen(navController = navController)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    val navController = rememberNavController()
    MyTheme(darkTheme = true) {
        MainScreen(navController = navController)
    }
}
