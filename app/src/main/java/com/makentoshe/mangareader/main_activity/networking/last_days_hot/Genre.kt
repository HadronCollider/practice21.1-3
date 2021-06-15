package com.makentoshe.mangareader.main_activity.networking.last_days_hot


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)