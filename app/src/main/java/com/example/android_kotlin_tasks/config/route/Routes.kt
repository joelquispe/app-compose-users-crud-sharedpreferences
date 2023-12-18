package com.example.android_kotlin_tasks.config.route

sealed class Routes(var route:String) {
    object  Login:Routes("login")
    object Users: Routes("users")
    object UsersForm : Routes("usersForm")
}