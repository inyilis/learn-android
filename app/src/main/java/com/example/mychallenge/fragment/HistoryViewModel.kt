package com.example.mychallenge.fragment

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychallenge.data.model.Histories
import com.example.mychallenge.data.repository.HistoryRepository
import com.example.mychallenge.util.EventResult
import java.lang.Exception

class HistoryViewModel: ViewModel() {

    var listHistories = listOf<Histories>()

    private lateinit var repository: HistoryRepository

    private var _historyLiveData = MutableLiveData<EventResult>(EventResult.Idle)

    val historyLiveData: LiveData<EventResult>
        get() = _historyLiveData

    private fun getHistoriesFromRepository() = repository.findAllHistory()

    fun setRepository(repository: HistoryRepository) {
        this.repository = repository
    }

    fun start() {
        updateInfo()
    }

    private fun updateInfo() {
        _historyLiveData.value = EventResult.Loading
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                val histories = getHistoriesFromRepository()
                listHistories = histories
                _historyLiveData.value = EventResult.Success(histories)
                Log.d("HistoryViewModel: ", "${histories}")
            } catch (e: Exception) {
                _historyLiveData.value = EventResult.Failed("Opss, someting wrong")
            }
        }, 1000)
    }

    fun addHistory(history: Histories) {
        repository.addHistory(history)
        updateInfo()
    }
}