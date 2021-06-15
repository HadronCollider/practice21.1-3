package com.makentoshe.mangareader.manga_main_page_activity.networking.chapters


import com.google.gson.annotations.SerializedName

data class Publisher(
    @SerializedName("name")
    val name: String
)