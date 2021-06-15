package com.makentoshe.mangareader.manga_main_page_activity.networking.description


import com.google.gson.annotations.SerializedName

data class ContinueReading(
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("tome")
    val tome: Int
)