package com.example.mychallenge.data.repository

import com.example.mychallenge.data.model.Pockets

interface PocketRepository {
    fun findAllPocket(): List<Pockets>
    fun addPocket(pocket: Pockets): Pockets
    fun delPocket(id: Int)
}