package com.makentoshe.androidgithubcitemplate.main_screen.lastdays_firstrecycler
import com.google.gson.annotations.SerializedName

// implementation group: 'com.squareup.retrofit2', name: 'converter-gson', version: '2.9.0'
data class last_days_and_first_recycler(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)