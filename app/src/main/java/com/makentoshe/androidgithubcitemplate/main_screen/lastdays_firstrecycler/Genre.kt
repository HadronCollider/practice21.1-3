package com.makentoshe.androidgithubcitemplate.main_screen.lastdays_firstrecycler
import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)