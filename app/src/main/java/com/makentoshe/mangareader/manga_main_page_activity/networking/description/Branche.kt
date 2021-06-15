package com.makentoshe.mangareader.manga_main_page_activity.networking.description


import com.google.gson.annotations.SerializedName

data class Branche(
    @SerializedName("count_chapters")
    val countChapters: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("publishers")
    val publishers: List<Publisher>,
    @SerializedName("subscribed")
    val subscribed: Boolean,
    @SerializedName("total_votes")
    val totalVotes: Int
)