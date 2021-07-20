package com.example.mychallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
            if (inputEmailLogin.text.isNullOrEmpty()) {
                Toast.makeText(this, "Input your email address", Toast.LENGTH_LONG).show()
            } else if (inputPasswordLogin.text.isNullOrEmpty()) {
                Toast.makeText(this, "Input your password", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
                Intent(this, MainActivity::class.java).also {
                it.putExtra(TAG_NAME, inputEmailLogin.text.toString())
                    startActivity(it)
                }
            }
        }
    }

    companion object {
        const val TAG_NAME = ""
    }
}