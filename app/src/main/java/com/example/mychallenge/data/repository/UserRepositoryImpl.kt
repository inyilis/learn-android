package com.example.mychallenge.data.repository

import com.example.mychallenge.data.model.Users

class UserRepositoryImpl: UserRepository {

    companion object {
        var userDb = mutableListOf(Users("admin", "admin@admin.com", "admin"))
        var profileDb = Users("Inyilis", "inyilis@gmail.com", "password")
    }

    override fun profile(): Users {
        return profileDb
    }

    override fun login(email: String, password: String): String {
        return "$email is login"
    }

    override fun signup(user: Users): String {
        userDb.add(user)
        return "Yee ${user.username}, you success sign up"
    }
}