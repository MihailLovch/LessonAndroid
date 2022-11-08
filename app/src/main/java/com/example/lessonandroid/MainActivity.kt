package com.example.lessonandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.lessonandroid.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val fragmentContainerId = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.botNavView)
        val navController = (supportFragmentManager.findFragmentById(R.id.main_fragments_container) as NavHostFragment).navController

        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, dest, _ ->
            when(dest.id){
                R.id.CFirstFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        viewBinding.botNavView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        viewBinding.botNavView.visibility = View.GONE
    }
}