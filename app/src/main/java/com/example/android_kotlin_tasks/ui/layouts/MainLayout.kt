package com.example.android_kotlin_tasks.ui.layouts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.android_kotlin_tasks.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(body: @Composable ()-> Unit,onTapFloat: ()->Unit) {
    return Scaffold (content = {
       body()
    }, floatingActionButton = {
        FloatingActionButton(onClick = onTapFloat, containerColor = Color.Transparent, modifier = Modifier
            .background(color = Color.Transparent)) {
            Icon(painterResource(id = R.drawable.plus), contentDescription = "plus")
        }
    })
}