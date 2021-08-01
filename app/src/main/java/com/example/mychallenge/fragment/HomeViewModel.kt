package com.example.mychallenge.fragment

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychallenge.data.model.Pockets
import com.example.mychallenge.data.repository.PocketRepository
import com.example.mychallenge.util.EventResult
import java.lang.Exception

class HomeViewModel: ViewModel() {

    var listPockets = listOf<Pockets>()

    private lateinit var repository: PocketRepository

    private var _pocketLiveData = MutableLiveData<EventResult>(EventResult.Idle)

    val pocketLiveData: LiveData<EventResult>
        get() = _pocketLiveData

    private fun getPocketsFromRepository() = repository.findAllPocket()

    fun setRepository(repository: PocketRepository) {
        this.repository = repository
    }
    fun start() {
        updateInfo()
    }

    private fun updateInfo() {
        _pocketLiveData.value = EventResult.Loading
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                val pockets = getPocketsFromRepository()
                listPockets = pockets
                _pocketLiveData.value = EventResult.Success(pockets)
                Log.d("HomeViewModel: ", "${pockets}")
            } catch (e: Exception) {
                _pocketLiveData.value = EventResult.Failed("Opss, someting wrong")
            }
        }, 1000)
    }

    fun addPocket(pocket: Pockets) {
        repository.addPocket(pocket)
        updateInfo()
    }

    fun delPocket(id: Int) {
        repository.delPocket(id)
        updateInfo()
    }
}