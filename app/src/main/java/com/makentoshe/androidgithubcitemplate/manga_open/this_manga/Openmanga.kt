package com.makentoshe.androidgithubcitemplate.manga_open
import com.google.gson.annotations.SerializedName
import com.makentoshe.androidgithubcitemplate.manga_open.this_manga.Content
import com.makentoshe.androidgithubcitemplate.manga_open.this_manga.Props

data class openmanga(
    @SerializedName("content")
    val content: Content,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)