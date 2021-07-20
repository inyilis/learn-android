package com.example.mychallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUp.setOnClickListener{
            if (inputUsernameSignUp.text.isNullOrEmpty()) {
                Toast.makeText(this, "Input your username", Toast.LENGTH_LONG).show()
            } else if (inputEmailSignUp.text.isNullOrEmpty()) {
                Toast.makeText(this, "Input your email address", Toast.LENGTH_LONG).show()
            } else if (inputPasswordSignUp.text.isNullOrEmpty()) {
                Toast.makeText(this, "Input your password", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Sign Up Success", Toast.LENGTH_LONG).show()
                Intent(this, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}