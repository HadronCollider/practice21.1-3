package com.makentoshe.androidgithubcitemplate.chapters.chapter_comments
import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("high")
    val high: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("mid")
    val mid: String
)