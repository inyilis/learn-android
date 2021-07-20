package com.example.mychallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameFromLoginActivity = intent.getStringExtra(LoginActivity.TAG_NAME)
        tvHello.text = "Hello $nameFromLoginActivity"
    }
}