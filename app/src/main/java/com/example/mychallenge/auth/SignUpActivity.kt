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
import com.example.mychallenge.data.model.Users
import com.example.mychallenge.databinding.ActivitySignUpBinding
import com.example.mychallenge.util.EventResult
import com.example.mychallenge.util.baseApp
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

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
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscriber()
        binding.apply {
            btnSignUp.setOnClickListener{
                if (inputUsernameSignUp.text.isNullOrEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Input your username", Toast.LENGTH_LONG).show()
                } else if (inputEmailSignUp.text.isNullOrEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Input your email address", Toast.LENGTH_LONG).show()
                } else if (inputPasswordSignUp.text.isNullOrEmpty()) {
                    Toast.makeText(this@SignUpActivity, "Input your password", Toast.LENGTH_LONG).show()
                } else {
                    var user = Users(inputUsernameSignUp.text.toString(), inputEmailSignUp.text.toString(), inputPasswordSignUp.text.toString())
                    viewModel.signup(user)
                }
            }
        }
    }

    companion object {
        const val TAG = "SignUpActivity"
    }

    private fun subscriber() {
        binding.apply {
            val signupObserve: Observer<EventResult> = Observer<EventResult> {
                when (it) {
                    is EventResult.Loading -> showProgressBar()
                    is EventResult.Success -> {
                        hideProgressBar()
                        Intent(this@SignUpActivity, MainActivity::class.java).also { nextPage ->
                            startActivity(nextPage)
                            finish()
                        }
                        Toast.makeText(this@SignUpActivity, "Sign Up Success", Toast.LENGTH_LONG).show()
                    }
                    is EventResult.Failed -> {
                        hideProgressBar()
                        Log.d(TAG, "Failed: ${it.errorMessage}")
                    }
                }
            }

            viewModel.eventLiveData.observe(this@SignUpActivity, signupObserve)
        }
    }

    fun hideProgressBar() {
        binding.signupProgressBar.visibility = View.GONE
        Log.d(TAG, "hideProgressBar")
    }

    fun showProgressBar() {
        binding.signupProgressBar.visibility = View.VISIBLE
        Log.d(TAG, "showProgressBar")
    }
}