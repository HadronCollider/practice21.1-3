package com.makentoshe.androidgithubcitemplate.data_classes.newchapter
import com.google.gson.annotations.SerializedName

data class Img(
    @SerializedName("high")
    val high: String,
    @SerializedName("low")
    val low: String,
    @SerializedName("mid")
    val mid: String
)