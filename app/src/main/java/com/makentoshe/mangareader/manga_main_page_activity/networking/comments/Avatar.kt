package com.makentoshe.mangareader.manga_main_page_activity.networking.comments


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("high")
    val high: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("mid")
    val mid: String
)