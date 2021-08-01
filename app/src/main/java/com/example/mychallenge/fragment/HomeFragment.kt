package com.example.mychallenge.fragment

import android.app.AlertDialog
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
import com.example.mychallenge.R
import com.example.mychallenge.adapter.PocketAdapter
import com.example.mychallenge.auth.LoginActivity
import com.example.mychallenge.data.model.Pockets
import com.example.mychallenge.data.repository.PocketRepositoryImpl
import com.example.mychallenge.databinding.FragmentHomeBinding
import com.example.mychallenge.util.EventResult
import kotlinx.android.synthetic.main.dialog_create_pocket.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), PocketAdapter.OnClickItem {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var pocketAdapter: PocketAdapter
    private lateinit var pocketRepository: PocketRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        pocketRepository = PocketRepositoryImpl()
        pocketAdapter = PocketAdapter(this)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setRepository(pocketRepository)
        viewModel.start()

        subscriber()
        binding.apply {
            recycleViewPockets.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = pocketAdapter
            }

            btnCreatePocket.setOnClickListener {
                createPocket()
            }

        }
    }

    private fun subscriber() {
        binding.apply {
            val pocketObserve: Observer<EventResult> = Observer<EventResult> {
                when (it) {
                    is EventResult.Loading -> showProgressBar()
                    is EventResult.Success -> {
                        hideProgressBar()
                        pocketAdapter.updateData(it.data as List<Pockets>)
                        tv_total_pocket.text = "Your total pocket is ${viewModel.listPockets.size}"
                    }
                    is EventResult.Failed -> {
                        hideProgressBar()
                        Log.d("TAG", "Failed: ${it.errorMessage}")
                    }
                }
            }

            viewModel.pocketLiveData.observe(viewLifecycleOwner, pocketObserve)
        }
    }

    fun createPocket() {
        val mDialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_create_pocket, null)
        val mBuilder = AlertDialog.Builder(activity).setView(mDialogView).setTitle("Create New Pocket")
        val mShowDialog = mBuilder.show()

        mDialogView.btn_create_pocktet.setOnClickListener {
            if (mDialogView.input_pocket_name.text.isNullOrEmpty()) {
                Toast.makeText(context, "Input your pocket name", Toast.LENGTH_SHORT).show()
            } else {
                mShowDialog.dismiss()
                val name = mDialogView.input_pocket_name.text.toString()
                viewModel.addPocket(Pockets(name, 0, 0))
            }
        }

        mDialogView.btn_cancel.setOnClickListener {
            mShowDialog.dismiss()
        }
    }

    fun hideProgressBar() {
        binding.homeProgressBar.visibility = View.GONE
        Log.d(LoginActivity.TAG, "hideProgressBar")
    }

    fun showProgressBar() {
        binding.homeProgressBar.visibility = View.VISIBLE
        Log.d(LoginActivity.TAG, "showProgressBar")
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun Onclick(position: Int) {
        Toast.makeText(context, "Click: ${viewModel.listPockets[position].name}", Toast.LENGTH_SHORT).show()
    }

    override fun DeletePocket(position: Int) {
        Toast.makeText(context, "Deleted pocket ${viewModel.listPockets[position].name} success", Toast.LENGTH_SHORT).show()
        viewModel.delPocket(position)
    }
}