package com.example.lessonandroid.entities

data class ListenedAlbum(
    val name: String,
    val cover: String,
){
    constructor(index:Int) : this(
        name = "Default Album $index",
        cover = "https://play-lh.googleusercontent.com/6UgEjh8Xuts4nwdWzTnWH8QtLuHqRMUB7dp24JYVE2xcYzq4HA8hFfcAbU-R-PC_9uA1"
    )
}
