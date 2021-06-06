package com.makentoshe.androidgithubcitemplate


import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        retrieveMangaList { list ->
            println(list)
        }
    }

    private fun retrieveMangaList(after: (List<MangaBitmapWrapper>) -> Unit) {
        val mangaList = listOf(
            "https://api.remanga.org/media/titles/military/9539534ce2ea8e6c12b4d1d81debe59f.jpg",
            "https://get.wallhere.com/photo/hill-with-blue-river-gorgeous-beauty-of-mountain-1550789.jpg",
            "https://w-dog.ru/wallpapers/9/1/298499341842887/osen-vodopady-kanchanaburi-provinciya-priroda-foto.jpg",
            "https://i.imgur.com/9I23DL3.jpg",
            "https://s1.1zoom.ru/b5050/994/260356-svetik_1920x1200.jpg",
            "https://c.wallhere.com/photos/ab/98/lake_round_coast_sky_mountains-1064574.jpg!d",
            "https://wallpapertag.com/wallpaper/full/3/b/a/522858-butterfly-desktop-backgrounds-1920x1200-photos.jpg",
            //"https://www.wallpaperup.com/uploads/wallpapers/2016/10/14/1025923/403030823b8c642fbab1fe0305865aca-1000.jpg",
            "https://c.wallhere.com/photos/40/80/1920x1200_px_bridges_forest_landscapes_Morning_nature_rivers_scenic-1783173.jpg!d",
            "https://c.wallhere.com/photos/d3/be/pond_geese_lodges_mill_wheel_summer-1054309.jpg!d",
        ).mapIndexed { index, url -> Manga("Title$index", "Genre$index", url) }

        lifecycleScope.launch(Dispatchers.IO) {
            val list = mangaList.map { manga ->
                val response = client.newCall(Request.Builder().url(manga.imageUrl).build()).execute()
                val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                MangaBitmapWrapper(manga, bitmap)
            }


            launch(Dispatchers.Main) { after.invoke(list) }
        }
    }

}

