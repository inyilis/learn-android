package com.example.mychallenge.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychallenge.adapter.HistoryAdapter
import com.example.mychallenge.data.model.Histories
import com.example.mychallenge.data.repository.HistoryRepositoryImpl
import com.example.mychallenge.databinding.FragmentHistoryBinding
import com.example.mychallenge.util.EventResult

class HistoryFragment : Fragment(), HistoryAdapter.OnClickItem {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyRepository: HistoryRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        historyRepository = HistoryRepositoryImpl()
        historyAdapter = HistoryAdapter(this)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setRepository(historyRepository)
        viewModel.start()

        subscriber()
        binding.apply {
            recycleViewHistories.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = historyAdapter
            }
        }
    }

    private fun subscriber() {
        binding.apply {
            val historyObserve: Observer<EventResult> = Observer<EventResult> {
                when (it) {
                    is EventResult.Loading -> showProgressBar()
                    is EventResult.Success -> {
                        hideProgressBar()
                        historyAdapter.updateData(it.data as List<Histories>)
                    }
                    is EventResult.Failed -> {
                        hideProgressBar()
                        Log.d("HistoryFragment", "Failed: ${it.errorMessage}")
                    }
                }
            }

            viewModel.historyLiveData.observe(viewLifecycleOwner, historyObserve)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }

    fun hideProgressBar() {
        binding.historyProgressBar.visibility = View.GONE
    }

    fun showProgressBar() {
        binding.historyProgressBar.visibility = View.VISIBLE
    }

    override fun Onclick(position: Int) {
        Toast.makeText(context, "Click: ${viewModel.listHistories[position].type}", Toast.LENGTH_SHORT).show()
    }
}