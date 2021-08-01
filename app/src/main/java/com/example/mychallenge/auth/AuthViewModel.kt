package com.example.mychallenge.auth

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychallenge.data.model.Users
import com.example.mychallenge.data.repository.UserRepository
import com.example.mychallenge.util.EventResult
import java.lang.Exception

class AuthViewModel(private val repositiry: UserRepository): ViewModel() {

    private var _eventLiveData = MutableLiveData<EventResult>(EventResult.Idle)

    val eventLiveData: LiveData<EventResult>
        get() = _eventLiveData

    private fun updateInfo(data: Any) {
        _eventLiveData.value = EventResult.Loading
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                _eventLiveData.value = EventResult.Success(data)
                Log.d("AuthViewModel: ", "${data}")
            } catch (e: Exception) {
                _eventLiveData.value = EventResult.Failed("Opss, someting wrong")
            }
        }, 1500)
    }

    fun login(email: String, password: String) {
        updateInfo(repositiry.login(email, password))
    }

    fun signup(user: Users) {
        updateInfo(repositiry.signup(user))
    }
}