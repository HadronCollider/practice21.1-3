package com.makentoshe.androidgithubcitemplate.main_activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.main_activity.networking.MainPageNetworkHandler
import com.makentoshe.androidgithubcitemplate.manga_main_page_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val networkHandler = MainPageNetworkHandler(client, lifecycleScope)

        val recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        getContent { content ->
            lifecycleScope.launch(Dispatchers.Main){
                recyclerView.adapter = MultipleElementsAdapter(lifecycleScope)
            }
        }

        /*setContentView(R.layout.activity_manga_main_page)

        val pager = findViewById<ViewPager>(R.id.viewPager)
        pager.adapter = ViewPagerAdapter(supportFragmentManager,lifecycleScope, makeDescription(), getChaptersList(), (0..10).map{i-> "Comment №$i"})

        val tabLayout = findViewById<TabLayout>(R.id.mainTabLayout)
        tabLayout.setupWithViewPager(pager)*/

    }

    private fun makeDescription(): MangaDescription= MangaDescription(
            MangaStatistics(50, 100, 200), "Empty description",
            (0..15).map{i-> "genre №$i"},
            Publisher("V.Corp Soulless", "Что-то пафосное", "https://api.remanga.org/media/publishers/jakinsteam/high_cover.jpg"),
            getMangasList())

    private fun getChaptersList() : List<Chapter> = (0..50).map{ i ->
        Chapter("Том 1. Глава $i.", "$i.06.2021", i, i* 10)
    }


    private fun getContent(after: (list: List<Pair<String, List<Manga>>>) -> Unit){
        lifecycleScope.launch(Dispatchers.Default){
            //val mangas = getMangasNoCallback()
            val result = listOf("Best voted",
            "Daily top",
            "Last days popular",
            "New chapters").map{ articleType ->
                Pair(articleType, getMangasList()) // , getMangasNoCallback()
            }
            after.invoke(result)
        }
    }

    /*private fun getMangas(after: (mangas: List<BitmapMangaWrapper>) -> Unit){
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
    }*/

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

}

