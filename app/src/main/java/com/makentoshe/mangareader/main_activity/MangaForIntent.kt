package com.makentoshe.mangareader.main_activity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MangaForIntent(val dir: String, val id: Int, val chaptersCount: Int) : Parcelable
