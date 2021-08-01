package com.example.mychallenge.data.repository

import com.example.mychallenge.data.model.Histories

interface HistoryRepository {
    fun findAllHistory(): List<Histories>
    fun addHistory(history: Histories): Histories
}