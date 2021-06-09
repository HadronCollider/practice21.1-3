package com.makentoshe.androidgithubcitemplate.networking.daily_top


import com.google.gson.annotations.SerializedName

data class DailyTop(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)