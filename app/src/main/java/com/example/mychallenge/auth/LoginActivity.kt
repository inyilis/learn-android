package com.example.mychallenge.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mychallenge.MainActivity
import com.example.mychallenge.databinding.ActivityLoginBinding
import com.example.mychallenge.util.EventResult
import com.example.mychallenge.util.baseApp
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return baseApp.getAuthViewModel() as T
        }
    }

    private val viewModel: AuthViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscriber()
        binding.apply {
            btnLogin.setOnClickListener{
                if (inputEmailLogin.text.isNullOrEmpty()) {
                    Toast.makeText(this@LoginActivity, "Input your email address", Toast.LENGTH_LONG).show()
                } else if (inputPasswordLogin.text.isNullOrEmpty()) {
                    Toast.makeText(this@LoginActivity, "Input your password", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.login(inputEmailLogin.text.toString(), inputPasswordLogin.text.toString())
                }
            }

        }
    }

    companion object {
        const val TAG = "LoginActivity"
    }

    private fun subscriber() {
        binding.apply {
            val loginObserve: Observer<EventResult> = Observer<EventResult> {
                when (it) {
                    is EventResult.Loading -> showProgressBar()
                    is EventResult.Success -> {
                        hideProgressBar()
                        Intent(this@LoginActivity, MainActivity::class.java).also { nextPage ->
                            startActivity(nextPage)
                            finish()
                        }
                        Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_LONG).show()
                    }
                    is EventResult.Failed -> {
                        hideProgressBar()
                        Log.d(TAG, "Failed: ${it.errorMessage}")
                    }
                }
            }

            viewModel.eventLiveData.observe(this@LoginActivity, loginObserve)
        }
    }

    fun hideProgressBar() {
        binding.loginProgressBar.visibility = View.GONE
        Log.d(TAG, "hideProgressBar")
    }

    fun showProgressBar() {
        binding.loginProgressBar.visibility = View.VISIBLE
        Log.d(TAG, "showProgressBar")
    }
}