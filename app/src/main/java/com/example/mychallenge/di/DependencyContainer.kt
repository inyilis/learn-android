package com.example.mychallenge.di

import com.example.mychallenge.auth.AuthViewModel
import com.example.mychallenge.data.repository.UserRepositoryImpl

class DependencyContainer {
    private val userRepository = UserRepositoryImpl()
    val authViewModel = AuthViewModel(userRepository)
}