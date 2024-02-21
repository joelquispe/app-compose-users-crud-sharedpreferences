package com.example.android_kotlin_tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_kotlin_tasks.config.route.Routes
import com.example.android_kotlin_tasks.ui.screens.login.LoginScreen
import com.example.android_kotlin_tasks.ui.screens.profile.Profile
import com.example.android_kotlin_tasks.ui.screens.users.UsersFormScreen
import com.example.android_kotlin_tasks.ui.screens.users.UsersScreen
import com.example.android_kotlin_tasks.ui.theme.AndroidkotlintasksTheme

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidkotlintasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController,
                        startDestination = Routes.Profile.route, builder = {
                            composable(Routes.Profile.route) { Profile(navController =
                            navigationController) }
                            composable(Routes.Users.route) { UsersScreen(navController = navigationController) }
                            composable(Routes.UsersForm.route) { UsersFormScreen(navController =
                            navigationController) }
                            composable(Routes.Login.route) {
                                LoginScreen(
                                    navController =
                                    navigationController
                                )
                            }

                        }
                    )

                }
            }
        }
    }
}



