
package com.makentoshe.androidgithubcitemplate

import android.graphics.Bitmap

class ImageResizer(private val image: Bitmap, newImageSize: ImageSize) {

    constructor(image: Bitmap, newWidth: Int) // Constructs Resizer saving image ratio
            : this(image, ImageSize(newWidth, (newWidth.toDouble() * image.height.toDouble() / image.width.toDouble()).toInt()))

    //private val newImageWidth: Int = if (newImageSize.width == 0) (newImageSize.height.toDouble() * (image.width.toDouble() / image.height.toDouble())).toInt() else newImageSize.width
    //private val newImageHeight: Int = if (newImageSize.height == 0) (newImageWidth.toDouble() * (image.height.toDouble() / image.width.toDouble())).toInt() else newImageSize.height
    private val oldSize = ImageSize(image.width, image.height)
    private var newSize: ImageSize = ImageSize(0, 0)
    init {
        val newImageWidth = newImageSize.width
        val newImageHeight = newImageSize.height

        if(newImageWidth == 0 && newImageHeight == 0){
            newSize = ImageSize(image.width, image.height)
        }else if(newImageWidth == 0){
            newSize = ImageSize((newImageSize.height.toDouble() * (image.width.toDouble() / image.height.toDouble())).toInt(),
                    newImageSize.height)
        } else if(newImageHeight == 0){
            newSize = ImageSize((newImageSize.width.toDouble() * (image.height.toDouble() / image.width.toDouble())).toInt(),
                newImageSize.height)
        } else newSize = newImageSize
    }

    fun getImageSize(): ImageSize = oldSize

    fun getNewImageSize(): ImageSize = newSize

    fun getImage(): Bitmap = image

    fun getResizedImage(): Bitmap = Bitmap.createScaledBitmap(image, newSize.width, newSize.height, true)
}