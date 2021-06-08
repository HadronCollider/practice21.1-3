package com.makentoshe.androidgithubcitemplate.main_screen.newchapters
import com.google.gson.annotations.SerializedName

data class new_chapters(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)