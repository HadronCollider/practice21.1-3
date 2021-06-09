package com.makentoshe.androidgithubcitemplate.networking.new_chapters

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("chapter_id")
    val chapterId: Int,
    @SerializedName("count_chapters")
    val countChapters: Int,
    @SerializedName("dir")
    val dir: String,
    @SerializedName("en_name")
    val enName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: Img,
    @SerializedName("is_hottest")
    val isHottest: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rus_name")
    val rusName: String,
    @SerializedName("tome")
    val tome: Int,
    @SerializedName("upload_date")
    val uploadDate: Int
)