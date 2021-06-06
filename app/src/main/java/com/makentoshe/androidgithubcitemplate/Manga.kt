package com.makentoshe.androidgithubcitemplate

import android.graphics.Bitmap

data class Manga(val title: String, val genre: String, val imageUrl: String)

data class MangaBitmapWrapper(val manga: Manga, val imageBitmap: Bitmap)