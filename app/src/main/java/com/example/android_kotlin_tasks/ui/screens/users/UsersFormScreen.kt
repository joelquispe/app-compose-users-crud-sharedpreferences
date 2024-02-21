package com.example.android_kotlin_tasks.ui.screens.users

import android.content.Context
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.android_kotlin_tasks.R
import com.example.android_kotlin_tasks.config.route.Routes
import com.example.android_kotlin_tasks.domain.models.UserModel
import com.example.android_kotlin_tasks.domain.services.SharedPreferencesService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersFormScreen(navController: NavController) {
    val sharedPreferencesService = SharedPreferencesService(context = LocalContext.current);
    val users by remember {
        mutableStateOf<MutableList<UserModel>>(mutableListOf())
    }
    var tfName by remember { mutableStateOf("") }
    var tfLastName by remember {
        mutableStateOf("")
    }
    var tfDni by remember {
        mutableStateOf("")
    }
    var title = "Crear Usuario"
    var isErrorTfName by remember{ mutableStateOf(false) }
    var isErrorTfLastName by remember{ mutableStateOf(false) }
    var isErrorTfDni by remember{ mutableStateOf(false) }

     fun getData(){
        val data = sharedPreferencesService.read("users")
        if(!data.isNullOrEmpty()){
            print("mi data users")
            users.addAll(Json.decodeFromString<List<UserModel>>(data))

            println(users)
        }
    }
    fun create(){
        if(tfName.isEmpty()){
            isErrorTfName = true;
            println("El campo nombre es requerido");
        }
        if(tfLastName.isEmpty()){
            isErrorTfLastName = true;
            println("El campo apellido es requerido");
        }
        if(tfDni.isEmpty()){
            isErrorTfDni = true
            println("El campo dni es requerido");
        }
        val userData: UserModel = UserModel(idUser = UUID.randomUUID().toString(),
            name = tfName, lastName = tfLastName, dni = tfDni)
        users.add(userData)
        val jsonData = Json.encodeToString(users)
        sharedPreferencesService.write("users",jsonData)
        println("Dato guardado")
    }
    LaunchedEffect(Unit ){
        getData()
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(painter = painterResource(id = R.drawable.back), contentDescription = "back")
        }

        Column (modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,){
            Column() {
                Text(text = title, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                OutlinedTextField(
                    isError = isErrorTfName,

                    value = tfName, onValueChange = {
                        tfName = it
                        isErrorTfName = tfName.isEmpty()
                    }, label = {
                        Text("Nombres")
                    }, colors = TextFieldDefaults
                        .textFieldColors(containerColor = Color.White, focusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color.Black),)
                if(isErrorTfName){
                    Text("El campo nombre es requerido", style = TextStyle(fontSize =10.sp, color = Color
                        .Red)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(value = tfLastName,
                    isError = isErrorTfLastName,
                    onValueChange = {
                    tfLastName = it
                    isErrorTfLastName = tfLastName.isEmpty()
                },
                    label = {
                    Text("Apellidos")
                }, colors = TextFieldDefaults
                    .textFieldColors(containerColor = Color.White, focusedIndicatorColor = Color.Black,
                        focusedLabelColor = Color.Black),)
                if(isErrorTfLastName){
                    Text("El campo apellidos es requerido", style = TextStyle(fontSize =10.sp,
                        color = Color
                        .Red)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = tfDni,
                    isError = isErrorTfDni,
                    onValueChange = {
                        tfDni = it
                        isErrorTfDni = tfDni.isEmpty()
                    }, label = {
                        Text("Dni")
                    }, colors = TextFieldDefaults
                        .textFieldColors(containerColor = Color.White, focusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color.Black),)
                if(isErrorTfDni){
                    Text("El campo dni es requerido", style = TextStyle(fontSize =10.sp,
                        color = Color
                            .Red)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = {
                  create()
                }, colors = ButtonDefaults.buttonColors(containerColor =Color.Black, contentColor =
                Color.White )) {
                    Text("Guardar")
                }
            }

        }

    }
}