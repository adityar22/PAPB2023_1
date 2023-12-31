package com.example.simpalaapps

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.simpalaapps.databinding.ActivityMainBinding
import com.example.simpalaapps.view.DashboardFragment
import com.example.simpalaapps.view.news.NewsFragment
import com.example.simpalaapps.view.report.FormReportFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
                    loadFragment(DashboardFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_form_report -> {
                    loadFragment(FormReportFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_news -> {
                    loadFragment(NewsFragment())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener  false
            }
        }

        // Tampilkan fragment pertama kali
        loadFragment(DashboardFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}