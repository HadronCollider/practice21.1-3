package com.makentoshe.mangareader.manga_main_page_activity.networking.comments


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("id")
    val id: Int,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("username")
    val username: String
)