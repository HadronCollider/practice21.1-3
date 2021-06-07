package com.makentoshe.androidgithubcitemplate


import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)


        getMangas{mangas ->
            lifecycleScope.launch(Dispatchers.Main){
                recyclerView.adapter = MyRecyclerViewAdapter(mangas)
            }
        }
    }

    /*private suspend fun generateList(): List<Manga> {
        val list = mutableListOf<Manga>()

        val urls: MutableList<String> = mutableListOf(
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
        )

        for (i in 0 until 9) {
            val image = lifecycleScope.async(Dispatchers.IO){
                Picasso.get().load(urls[i]).get()}
            list.add(
                Manga("manga №$i", "Any genre", urls[i], image.await()))
        }
        return list
    }*/

    private fun getMangas(after: (mangas: List<BitmapMangaWrapper>) -> Unit){
        val list = getMangasList()
        lifecycleScope.launch(Dispatchers.IO){
            val mangas = list.map{ manga ->
                val response = client.newCall(Request.Builder().url(manga.imageUrl).build()).execute()
                val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                BitmapMangaWrapper(manga, bitmap)
            }
            after.invoke(mangas)
        }
    }


    private fun getMangasList(): List<Manga> = listOf(
        "https://api.remanga.org/media/titles/military/9539534ce2ea8e6c12b4d1d81debe59f.jpg",
        "https://get.wallhere.com/photo/hill-with-blue-river-gorgeous-beauty-of-mountain-1550789.jpg",
        "https://w-dog.ru/wallpapers/9/1/298499341842887/osen-vodopady-kanchanaburi-provinciya-priroda-foto.jpg",
        "https://i.imgur.com/9I23DL3.jpg",
        "https://s1.1zoom.ru/b5050/994/260356-svetik_1920x1200.jpg",
        "https://c.wallhere.com/photos/ab/98/lake_round_coast_sky_mountains-1064574.jpg!d",
        "https://wallpapertag.com/wallpaper/full/3/b/a/522858-butterfly-desktop-backgrounds-1920x1200-photos.jpg",
        "https://c.wallhere.com/photos/40/80/1920x1200_px_bridges_forest_landscapes_Morning_nature_rivers_scenic-1783173.jpg!d",
        "https://c.wallhere.com/photos/d3/be/pond_geese_lodges_mill_wheel_summer-1054309.jpg!d"
    ).mapIndexed { index, url -> Manga("manga №$index", "Any genre", url) }


    /*private fun getScreenSize(): ImageSize{
        val displayMetrics = DisplayMetrics()
        val windowsManager = applicationContext.getSystemService(WINDOW_SERVICE) as WindowManager

        windowsManager.defaultDisplay.getMetrics(displayMetrics)
        Log.d("SCREEN_METRICS", "${displayMetrics.widthPixels}x${displayMetrics.heightPixels}")

        return ImageSize(displayMetrics.widthPixels, displayMetrics.heightPixels)
    }*/

}

