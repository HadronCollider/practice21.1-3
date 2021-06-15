package com.makentoshe.mangareader.main_activity.networking.new_chapters

import com.google.gson.annotations.SerializedName

data class NewChapters(
    @SerializedName("content")
    val content: List<NewChaptersManga>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)