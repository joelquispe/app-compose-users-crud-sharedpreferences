package com.example.android_kotlin_tasks.ui.screens.profile


import android.net.Uri
import android.widget.ImageView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun  Profile(modifier:Modifier = Modifier,navController:NavController){
    var uriImage by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(contract = ActivityResultContracts
        .GetContent(),
        onResult ={
        print("mi image")
        uriImage = it
            print(it);
            println(uriImage)
    } )
    var uriImageCamera by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }
    val onTakePicture = rememberLauncherForActivityResult(contract = ActivityResultContracts
        .TakePicture()){
        println(it)
    }
    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        Button(onClick = {
        singlePhotoPicker.launch("image/")
        }) {
            Text(text = "Seleccionar foto")
        }
        Button(onClick = {
            
          onTakePicture.launch(uriImageCamera)
        }) {
            Text(text = "Tomar foto")
        }
        if(uriImage != null){
            AsyncImage(model = uriImage, contentDescription ="image" ,
                modifier = modifier.width(50.dp))
        }

    }

}