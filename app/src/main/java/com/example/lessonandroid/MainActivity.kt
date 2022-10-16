package com.example.lessonandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.databinding.ActivityMainBinding
import com.example.lessonandroid.fragments.RecyclerViewFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val fragmentContainerId = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().
                add(
                    fragmentContainerId,
                    RecyclerViewFragment.getInstance(),
                    RecyclerViewFragment.RV_FRAGMENT_TAG
                ).commit()
    }
}