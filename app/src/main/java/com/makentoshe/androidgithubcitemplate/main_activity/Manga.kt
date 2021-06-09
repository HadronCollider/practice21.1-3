package com.makentoshe.androidgithubcitemplate.main_activity

import android.graphics.Bitmap


data class Manga(val title: String, val genre: String, val imageUrl: String)
data class MangaNewChapter(val title: String, val chapter: String, val uploadDate: Int, val imageUrl: String) // uploadDate - millis ago chapter was published