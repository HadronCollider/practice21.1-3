package com.makentoshe.androidgithubcitemplate.main_activity.networking.new_chapters

import com.google.gson.annotations.SerializedName

data class BookmarkType(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)