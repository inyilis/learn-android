package com.example.mychallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mychallenge.fragment.HistoryFragment
import com.example.mychallenge.fragment.HomeFragment
import com.example.mychallenge.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val homeFragment = HomeFragment()
    val historyFragment = HistoryFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragment()
        loadFragment(homeFragment)
//        val nameFromLoginActivity = intent.getStringExtra(LoginActivity.TAG_NAME)
//        tvHello.text = "Hello $nameFromLoginActivity"
    }

    private fun setupFragment() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    loadFragment(homeFragment)
                    true
                }

                R.id.nav_history -> {
                    loadFragment(historyFragment)
                    true
                }

                R.id.nav_profile -> {
                    loadFragment(profileFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container_main.id, fragment)
        transaction.commit()
    }
}