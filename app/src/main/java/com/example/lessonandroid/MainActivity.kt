package com.example.lessonandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lessonandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val fragmentContainerId: Int = R.id.main_fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            return;
        }
        supportFragmentManager.beginTransaction()
            .add(
                fragmentContainerId,
                MainFragment.getInstance(savedInstanceState),
                MainFragment.MAIN_PAGE_FRAGMENT_TAG
            )
            .commit()
    }

}