package com.example.mychallenge

import android.app.Application
import com.example.mychallenge.di.DependencyContainer

class BaseAplication: Application() {

    private val container: DependencyContainer by lazy {
        DependencyContainer()
    }

    fun getAuthViewModel() = container.authViewModel
}