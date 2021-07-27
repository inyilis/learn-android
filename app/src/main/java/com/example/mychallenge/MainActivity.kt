package com.example.mychallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mychallenge.fragment.HistoryFragment
import com.example.mychallenge.fragment.HomeFragment
import com.example.mychallenge.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragment()
//        val nameFromLoginActivity = intent.getStringExtra(LoginActivity.TAG_NAME)
//        tvHello.text = "Hello $nameFromLoginActivity"
    }

    private fun setupFragment() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.findNavController()
        bottom_navigation.setupWithNavController(navController)
    }
}