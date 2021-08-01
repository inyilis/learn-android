package com.example.mychallenge.data.repository

import com.example.mychallenge.data.model.Users

interface UserRepository {
    fun profile(): Users
    fun login(email: String, password: String): String
    fun signup(user: Users): String
}