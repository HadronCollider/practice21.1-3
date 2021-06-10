package com.makentoshe.androidgithubcitemplate.chapters.chapter_comments
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("count_replies")
    val countReplies: Int,
    @SerializedName("date")
    val date: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_pinned")
    val isPinned: Boolean,
    @SerializedName("is_spoiler")
    val isSpoiler: Boolean,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: User
)