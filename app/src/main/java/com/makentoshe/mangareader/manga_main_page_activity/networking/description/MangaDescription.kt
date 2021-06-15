package com.makentoshe.mangareader.manga_main_page_activity.networking.description


import com.google.gson.annotations.SerializedName

data class MangaDescription(
    @SerializedName("content")
    val content: DescriptionContent,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)