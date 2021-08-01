package com.example.mychallenge.data.repository

import com.example.mychallenge.data.model.Histories
import java.time.LocalDateTime

class HistoryRepositoryImpl: HistoryRepository {

    companion object {
        var historyDB = mutableListOf(
            Histories(true, 1, 1000000, LocalDateTime.now().toString()),
            Histories(true, 1, 1000000, LocalDateTime.now().toString())
        )
    }

    override fun findAllHistory(): List<Histories> {
        return historyDB
    }

    override fun addHistory(history: Histories): Histories {
        historyDB.add(history)
        return history
    }
}