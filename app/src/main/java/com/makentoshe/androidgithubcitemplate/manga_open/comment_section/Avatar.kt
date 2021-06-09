package com.makentoshe.androidgithubcitemplate.manga_open.comment_section
import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("high")
    val high: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("mid")
    val mid: String
)