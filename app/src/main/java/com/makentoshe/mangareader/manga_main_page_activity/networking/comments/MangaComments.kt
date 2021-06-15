package com.makentoshe.mangareader.manga_main_page_activity.networking.comments


import com.google.gson.annotations.SerializedName

data class MangaComments(
    @SerializedName("content")
    val content: List<CommentsContent>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)