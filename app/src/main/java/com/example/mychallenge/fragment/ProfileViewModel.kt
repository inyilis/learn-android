package com.example.mychallenge.fragment

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychallenge.data.model.Pockets
import com.example.mychallenge.data.repository.UserRepository
import com.example.mychallenge.util.EventResult
import java.lang.Exception

class ProfileViewModel: ViewModel() {

    private lateinit var repository: UserRepository

    private var _userLiveData = MutableLiveData<EventResult>(EventResult.Idle)

    val userLiveData: LiveData<EventResult>
        get() = _userLiveData

    fun getProfileFromRepository() = repository.profile()

    fun setRepository(repository: UserRepository) {
        this.repository = repository
    }
    fun start() {
        updateInfo()
    }

    private fun updateInfo() {
        _userLiveData.value = EventResult.Loading
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                val user = getProfileFromRepository()
                _userLiveData.value = EventResult.Success(user)
            } catch (e: Exception) {
                _userLiveData.value = EventResult.Failed("Opss, someting wrong")
            }
        }, 1000)
    }
}