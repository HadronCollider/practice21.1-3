package com.makentoshe.mangareader.main_activity.networking.last_days_hot


import com.google.gson.annotations.SerializedName

data class LastDaysHot(
    @SerializedName("content")
    val content: List<LastDaysHotManga>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)