package com.example.mychallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mychallenge.auth.LoginActivity
import com.example.mychallenge.auth.SignUpActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnToLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnToSignUp.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}