package com.example.android_kotlin_tasks.ui.screens.login

import android.content.Context
import android.util.JsonReader
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android_kotlin_tasks.R
import com.example.android_kotlin_tasks.config.route.Routes

import com.example.android_kotlin_tasks.ui.theme.AndroidkotlintasksTheme
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    var tfUsername by remember {
        mutableStateOf("")
    }
    var tfPassword by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(15.dp), verticalArrangement = Arrangement.Center,Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
            modifier = Modifier.width(110.dp))
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(value = tfUsername, onValueChange = {
            tfUsername = it
        }, label = {
            Text("Nombre de Usuario")
        }, colors = TextFieldDefaults
            .textFieldColors(containerColor = Color.White, focusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black),)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = tfPassword, onValueChange = {
           tfPassword = it
        }, label = {
            Text("Contrassseña")
        }, visualTransformation = PasswordVisualTransformation(), colors = TextFieldDefaults
            .textFieldColors(containerColor = Color.White, focusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black),)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
        navController.navigate(Routes.Users.route)
        }, colors = ButtonDefaults.buttonColors(containerColor =Color.Black, contentColor =
        Color.White )) {
            Text("Iniciar Sesión")
        }
    }
}

