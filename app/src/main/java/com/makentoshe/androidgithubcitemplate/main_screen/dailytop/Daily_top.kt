package com.makentoshe.androidgithubcitemplate.main_screen.dailytop
import com.google.gson.annotations.SerializedName

data class daily_top(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)