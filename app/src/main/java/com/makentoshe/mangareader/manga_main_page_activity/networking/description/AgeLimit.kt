package com.makentoshe.mangareader.manga_main_page_activity.networking.description


import com.google.gson.annotations.SerializedName

data class AgeLimit(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)