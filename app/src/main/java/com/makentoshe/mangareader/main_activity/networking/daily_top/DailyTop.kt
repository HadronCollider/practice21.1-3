package com.makentoshe.mangareader.main_activity.networking.daily_top


import com.google.gson.annotations.SerializedName

data class DailyTop(
    @SerializedName("content")
    val content: List<DailyTopManga>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)