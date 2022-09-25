package com.example.lessonandroid

import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import android.os.Bundle
import com.example.lessonandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val containerId: Int = R.id.main_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(containerId,FirstFragment.getInstance(),FirstFragment.FIRST_PAGE_FRAGMENT_TAG)
            .commit()
    }
}