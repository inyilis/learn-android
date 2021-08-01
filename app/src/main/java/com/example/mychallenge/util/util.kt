package com.example.mychallenge.util

import androidx.appcompat.app.AppCompatActivity
import com.example.mychallenge.BaseAplication

val AppCompatActivity.baseApp: BaseAplication
    get() = (application as BaseAplication)