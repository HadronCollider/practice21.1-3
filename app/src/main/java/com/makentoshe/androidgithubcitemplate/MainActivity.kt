package com.makentoshe.androidgithubcitemplate


import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.CopyOnWriteArrayList


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val searchLine = findViewById<EditText>(R.id.searchTextView) // Search line TODO add search of mangas -> redirect to another Activity

        val recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

            getContent { content ->
                lifecycleScope.launch(Dispatchers.Main){
                    recyclerView.adapter = MultipleElementsAdapter(content, lifecycleScope)
                }
            }



        /*val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)


        getMangas{mangas ->
            lifecycleScope.launch(Dispatchers.Main){
                recyclerView.adapter = TopRecyclerViewAdapter(mangas)
            }
        }*/
    }

    private fun getContent(after: (list: List<Pair<String, List<Manga>>>) -> Unit){
        lifecycleScope.launch(Dispatchers.Default){
            //val mangas = getMangasNoCallback()
            val result = listOf("Top Feed",
            "Popular today",
            "Hot news",
            "New chapters").map{ articleType ->
                Pair(articleType, getMangasList()) // , getMangasNoCallback()
            }
            after.invoke(result)
        }
    }

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

    private suspend fun getMangasNoCallback(): List<BitmapMangaWrapper>{
        val list = getMangasList()
        val result: MutableList<BitmapMangaWrapper> = mutableListOf()

        list.forEach { manga ->
            val bitmap = lifecycleScope.async(Dispatchers.IO) {
                val response = client.newCall(Request.Builder().url(manga.imageUrl).build()).execute()
                BitmapFactory.decodeStream(response.body?.byteStream())
            }
            result.add(BitmapMangaWrapper(manga, bitmap.await()))
        }

        println(result)
        return result
    }

    private fun getMangasList(): List<Manga> = listOf(
        "https://api.remanga.org/media/titles/military/9539534ce2ea8e6c12b4d1d81debe59f.jpg",
        "https://api.remanga.org/media/titles/baby-impress/e2889c2b7b2cdcb847809e4d4c5db365.jpg",
        "https://api.remanga.org/media/titles/blonde-elemental/2af7981cc78d8445ce7e07ee845f67ac.jpg",
        "https://api.remanga.org/media/titles/cha-chonbis-altruism/f51598eae5de713c4e35d4dc6aca27a2.jpg",
        "https://api.remanga.org/media/titles/hallym-gymnasium/7d0a50512ced6236ced3b35ca0560f7d.jpg",
        "https://api.remanga.org/media/titles/heavenly-jewel-change/83f23823616d5998bd59f4c05aaf5726.jpg",
        "https://api.remanga.org/media/titles/hes-got-three-tyrant-brothers/969647cc8cbe79c4f635cc71202be047.jpg",
        "https://api.remanga.org/media/titles/i-was-caught-up-in-a-hero-summoning-but-that-world-is-at-peace/mid_cover.jpg",
        "https://api.remanga.org/media/titles/i-will-just-live-as-a-villain/dee052adaba1b4028ae2938b9b975252.jpg"
    ).mapIndexed { index, url -> Manga("manga №$index", "Any genre", url) }


    /*private fun getScreenSize(): ImageSize{
        val displayMetrics = DisplayMetrics()
        val windowsManager = applicationContext.getSystemService(WINDOW_SERVICE) as WindowManager

        windowsManager.defaultDisplay.getMetrics(displayMetrics)
        Log.d("SCREEN_METRICS", "${displayMetrics.widthPixels}x${displayMetrics.heightPixels}")

        return ImageSize(displayMetrics.widthPixels, displayMetrics.heightPixels)
    }*/

}

