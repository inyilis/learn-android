package com.example.mychallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge.data.model.Histories
import com.example.mychallenge.databinding.ListHistoriesBinding

class HistoryAdapter(private val onClickItem: OnClickItem): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var histories = listOf<Histories>()

    class HistoryViewHolder(val binding: ListHistoriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ListHistoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        with (holder) {
            with (histories[position]) {
                if(this.type) {
                    binding.tvBuyPocket.text = "Buy Pocket"
                } else {
                    binding.tvBuyPocket.text = "Sell Pocket"
                }
                binding.tvQtyPocket.text = "${this.qty} gr"
                binding.tvAmountPocket.text = "Rp ${this.amount}"
                binding.tvDateTransaction.text = this.date
                binding.cardHistories.setOnClickListener {
                    onClickItem.Onclick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = histories.size

    fun updateData(history: List<Histories>) {
        this.histories = history
        notifyDataSetChanged()
    }

    interface OnClickItem {
        fun Onclick(position: Int)
    }

}