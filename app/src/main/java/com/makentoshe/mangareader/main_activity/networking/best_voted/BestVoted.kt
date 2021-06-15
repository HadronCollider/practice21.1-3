package com.makentoshe.mangareader.main_activity.networking.best_voted

import com.google.gson.annotations.SerializedName

// implementation group: 'com.squareup.retrofit2', name: 'converter-gson', version: '2.9.0'
data class BestVoted(
    @SerializedName("content")
    val content: List<BestVotedManga>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)