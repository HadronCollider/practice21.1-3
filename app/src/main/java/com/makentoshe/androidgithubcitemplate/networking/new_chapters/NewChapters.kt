package com.makentoshe.androidgithubcitemplate.networking.new_chapters

import com.google.gson.annotations.SerializedName

data class NewChapters(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)