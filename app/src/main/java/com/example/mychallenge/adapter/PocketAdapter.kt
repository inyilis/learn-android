package com.example.mychallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge.data.model.Pockets
import com.example.mychallenge.databinding.ListPocketsBinding

class PocketAdapter(private val onClickItem: OnClickItem): RecyclerView.Adapter<PocketAdapter.PocketViewHolder>() {

    private var pockets = listOf<Pockets>()

    class PocketViewHolder(val binding: ListPocketsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PocketViewHolder {
        val binding = ListPocketsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PocketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PocketViewHolder, position: Int) {
        with (holder) {
            with (pockets[position]) {
                binding.tvPocketName.text = this.name
                binding.tvPocketAmount.text = "Rp ${this.amount}"
                binding.tvPocketQty.text = "${this.qty} gr"
                binding.cardPocket.setOnClickListener {
                    onClickItem.Onclick(position)
                }
                binding.ivDeletePocket.setOnClickListener {
                    onClickItem.DeletePocket(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = pockets.size

    fun updateData(pocket: List<Pockets>) {
        this.pockets = pocket
        notifyDataSetChanged()
    }

    interface OnClickItem {
        fun Onclick(position: Int)
        fun DeletePocket(position: Int)
    }
}