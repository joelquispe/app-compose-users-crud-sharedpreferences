package com.example.android_kotlin_tasks.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    var idUser: String,
    var name: String,
    var lastName: String,
    var dni: String
)


