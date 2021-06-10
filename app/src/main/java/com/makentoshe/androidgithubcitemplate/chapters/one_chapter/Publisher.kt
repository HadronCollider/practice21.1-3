package com.makentoshe.androidgithubcitemplate.chapters.one_chapter
import com.google.gson.annotations.SerializedName
import com.makentoshe.androidgithubcitemplate.chapters.one_chapter.Img

data class Publisher(
    @SerializedName("dir")
    val dir: String,
    @SerializedName("donate_page_text")
    val donatePageText: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: Img,
    @SerializedName("name")
    val name: String,
    @SerializedName("paid_subscription")
    val paidSubscription: Any,
    @SerializedName("show_donate")
    val showDonate: Boolean
)