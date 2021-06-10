package com.makentoshe.androidgithubcitemplate.chapters.chapter_comments
import com.google.gson.annotations.SerializedName

data class comments(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)