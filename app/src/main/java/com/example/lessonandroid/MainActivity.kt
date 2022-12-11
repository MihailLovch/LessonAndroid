package com.example.lessonandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.example.lessonandroid.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private var adapter: MemeAdapter? = null
    private var glide : RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        glide = Glide.with(this)
        adapter = MemeAdapter(
            LinksRepository.items,
            Glide.with(this),
            lifecycleScope
        )
        viewBinding.imagesVp.adapter = adapter

        viewBinding.startBtn.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                launch {preloadAllImage(0,4)}
                launch { preloadAllImage(5,9)}
                launch {preloadAllImage(10,14)}
            }
        }

        setContentView(viewBinding.root)
    }

    private fun preloadAllImage(start: Int, end: Int){
        for (i in start..end){
            glide?.load(LinksRepository.items[i])?.preload()
            LinksRepository.items[i].loaded=  true
        }
    }
}