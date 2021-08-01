package com.example.mychallenge.data.repository

import com.example.mychallenge.data.model.Pockets

class PocketRepositoryImpl: PocketRepository {

    companion object {
        var pocketDb = mutableListOf(
            Pockets("Tabungan Beli Mobil", 1000000, 1),
            Pockets("Tabungan Beli Rumah", 1000000, 1)
        )
    }

    override fun findAllPocket(): List<Pockets> {
        return pocketDb
    }

    override fun addPocket(pocket: Pockets): Pockets {
        pocketDb.add(pocket)
        return pocket
    }

    override fun delPocket(id: Int) {
        pocketDb.removeAt(id)
    }
}