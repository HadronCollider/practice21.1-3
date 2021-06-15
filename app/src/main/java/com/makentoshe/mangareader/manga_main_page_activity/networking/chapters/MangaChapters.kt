package com.makentoshe.mangareader.manga_main_page_activity.networking.chapters


import com.google.gson.annotations.SerializedName

data class MangaChapters(
    @SerializedName("content")
    val content: List<ChaptersContent>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)