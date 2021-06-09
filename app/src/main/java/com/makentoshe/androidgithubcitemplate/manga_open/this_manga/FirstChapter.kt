package com.makentoshe.androidgithubcitemplate.manga_open.this_manga
import com.google.gson.annotations.SerializedName

data class FirstChapter(
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("tome")
    val tome: Int
)