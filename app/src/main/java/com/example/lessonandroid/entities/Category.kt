package com.example.lessonandroid.entities

import com.example.lessonandroid.R

data class Category(
    var imageId:Int,
    var name: String,
){
    constructor(index : Int): this(
        imageId = R.drawable.ic_baseline_delete_24,
        name = "Default Category $index"
    )
}
