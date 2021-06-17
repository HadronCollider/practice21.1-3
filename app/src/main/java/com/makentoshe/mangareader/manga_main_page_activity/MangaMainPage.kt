package com.makentoshe.mangareader.manga_main_page_activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.makentoshe.mangareader.R
import com.makentoshe.mangareader.main_activity.MangaForIntent
import com.makentoshe.mangareader.manga_main_page_activity.networking.MangaMainPageNetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class MangaMainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manga_main_page)

        val manga: MangaForIntent? = intent.getParcelableExtra("manga")

        setToolbarImage(manga!!)

        val pager = findViewById<ViewPager>(R.id.viewPager)
        pager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycleScope, manga!!)
        val tabLayout = findViewById<TabLayout>(R.id.mainTabLayout)
        tabLayout.setupWithViewPager(pager)
    }


    private fun setToolbarImage(manga: MangaForIntent) {
        val client = OkHttpClient()
        val networkHandler = MangaMainPageNetworkHandler(client, lifecycleScope)
        networkHandler.getMangaDescription(manga.dir) { description, _ ->
            val imageUrl = description.img.high

            lifecycleScope.launch(Dispatchers.IO) {
                val response = client.newCall(
                    Request.Builder().url("https://api.remanga.org${imageUrl}").build()
                ).execute()
                if (response.isSuccessful) {

                    var bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                    val newWidth = getScreenSize().first

                    bitmap = Bitmap.createScaledBitmap(bitmap, newWidth,
                        (bitmap.height.toDouble() * newWidth.toDouble() / bitmap.width.toDouble()).toInt(), true)

                    lifecycleScope.launch(Dispatchers.Main) {
                        findViewById<Toolbar>(R.id.toolbar).background = BitmapDrawable(resources, bitmap)
                    }
                } else {
                    println(response.message)
                }
            }

        }
    }


    private fun getScreenSize(): Pair<Int, Int>{
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
    }
}