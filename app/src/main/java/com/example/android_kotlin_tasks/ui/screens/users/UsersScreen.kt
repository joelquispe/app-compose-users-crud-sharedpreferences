package com.example.android_kotlin_tasks.ui.screens.users

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_kotlin_tasks.config.route.Routes
import com.example.android_kotlin_tasks.domain.models.UserModel
import com.example.android_kotlin_tasks.domain.services.SharedPreferencesService
import com.example.android_kotlin_tasks.ui.layouts.MainLayout
import kotlinx.serialization.json.Json


@Composable
fun UsersScreen(navController: NavController) {
    val sharedPreferencesService = SharedPreferencesService(context = LocalContext.current)
    val usersList = remember{ mutableStateOf<List<UserModel>>(emptyList()) }
    suspend fun getData(){
        var data = sharedPreferencesService.read("user") ?: null
        println(data)
        val users: List<UserModel> = Json.decodeFromString<List<UserModel>>(data ?: "")
        println(users)
        usersList.value = users

        println("mi data")
    }
    LaunchedEffect(Unit ){
        println("init data")
        getData()
    }
    MainLayout(onTapFloat = { navController.navigate(Routes.UsersForm.route)
    }, body = {
        LazyColumn(modifier = Modifier.padding(horizontal = 15.dp)) {
            items(items = usersList.value) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp), shape =
                    RoundedCornerShape(14.dp), elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp,

                        ), colors = CardDefaults.cardColors(containerColor = Color.Black)
                ) {
                    Column(modifier = Modifier.padding(vertical = 13.dp, horizontal = 10.dp)) {

                        Text(text = "${item.name} ${item.lastName}", color = Color.White)
                        Text(text = "Dni : ${item.dni}",color = Color.White)
                    }

                }

            }
        }
    })
}

fun fetchUsersList(): List<UserModel> {
    return listOf<UserModel>(
        UserModel(
            idUser = "1", name = "Joel", lastName = "Rodriguez", dni =
            "12345678"
        ), UserModel(
            idUser = "2", name = "Carlos", lastName = "Rodriguez", dni =
            "12345678"
        ), UserModel(
            idUser = "3", name = "Carla", lastName = "Rodriguez", dni =
            "12345678"
        ), UserModel(
            idUser = "4", name = "Piero", lastName = "Rodriguez", dni =
            "12345678"
        ), UserModel(
            idUser = "5", name = "Jorge", lastName = "Rodriguez", dni =
            "12345678"
        )
    )
}
