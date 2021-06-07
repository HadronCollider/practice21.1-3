
package com.makentoshe.androidgithubcitemplate

import android.graphics.Bitmap


data class Manga(val title: String, val genre: String, val imageUrl: String)
data class BitmapMangaWrapper(val manga: Manga, val image: Bitmap)