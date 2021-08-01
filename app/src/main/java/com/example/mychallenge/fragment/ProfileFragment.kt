package com.example.mychallenge.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mychallenge.data.repository.UserRepositoryImpl
import com.example.mychallenge.databinding.FragmentProfileBinding
import com.example.mychallenge.util.EventResult

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var userRepository: UserRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        userRepository = UserRepositoryImpl()
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setRepository(userRepository)
        viewModel.start()

        subscriber()
    }

    private fun subscriber() {
        binding.apply {
            val pocketObserve: Observer<EventResult> = Observer<EventResult> {
                when (it) {
                    is EventResult.Loading -> showProgressBar()
                    is EventResult.Success -> {
                        hideProgressBar()
                        tvUserName.text = viewModel.getProfileFromRepository().username
                        tvEmailAddress.text = viewModel.getProfileFromRepository().email
                    }
                    is EventResult.Failed -> {
                        hideProgressBar()
                        Log.d("ProfileFragment", "Failed: ${it.errorMessage}")
                    }
                }
            }

            viewModel.userLiveData.observe(viewLifecycleOwner, pocketObserve)
        }
    }

    fun hideProgressBar() {
        binding.profileProgressBar.visibility = View.GONE
    }

    fun showProgressBar() {
        binding.profileProgressBar.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}