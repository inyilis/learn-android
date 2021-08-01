package com.example.mychallenge.util

sealed class EventResult {
    object Idle: EventResult()
    object Loading: EventResult()
    data class Success(val data: Any): EventResult()
    data class Failed(val errorMessage: String): EventResult()
}
