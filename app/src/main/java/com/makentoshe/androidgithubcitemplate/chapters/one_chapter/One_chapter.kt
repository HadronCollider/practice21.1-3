package com.makentoshe.androidgithubcitemplate.chapters.one_chapter
import com.google.gson.annotations.SerializedName

data class one_chapter(
    @SerializedName("content")
    val content: Content,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)