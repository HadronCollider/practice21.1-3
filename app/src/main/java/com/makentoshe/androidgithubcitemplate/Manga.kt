
package com.makentoshe.androidgithubcitemplate

import android.graphics.Bitmap
import android.media.Image
import kotlinx.coroutines.coroutineScope

data class Manga(val title: String, val genre: String, val imageUrl: String, val image: Bitmap) {}