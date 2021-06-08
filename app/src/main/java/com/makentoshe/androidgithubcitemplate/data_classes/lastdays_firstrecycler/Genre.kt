package com.makentoshe.androidgithubcitemplate.data_classes.lastdays_firstrecycler
import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)