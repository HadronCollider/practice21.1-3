package com.makentoshe.androidgithubcitemplate.networking

import com.google.gson.Gson
import com.makentoshe.androidgithubcitemplate.api.MainScreenAPI
import com.makentoshe.androidgithubcitemplate.main_activity.Manga
import com.makentoshe.androidgithubcitemplate.main_activity.MangaNewChapter
import com.makentoshe.androidgithubcitemplate.networking.basic_contents.CategoriesContent
import com.makentoshe.androidgithubcitemplate.networking.new_chapters.NewChapters
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class MainPageNetworkHandler(client: OkHttpClient) {
    private val gson = Gson()
    private var api: MainScreenAPI
    init{
        val retrofit = Retrofit.Builder().client(client).baseUrl("https://api.remanga.org/").build()
        api = retrofit.create(MainScreenAPI::class.java)
    }

    private fun makeMangaList(content: CategoriesContent): List<Manga>{
        return content.content.map { content ->
            Manga(content.enName, content.genres[0].name, content.coverHigh)
        }
    }

    private fun makeNewChaptersList(content: NewChapters): List<MangaNewChapter>{
        return content.content.map { content ->
            MangaNewChapter(content.enName, content.chapter, content.uploadDate)
        }
    }

    fun getLastDaysHotManga(): List<Manga> {
        val response = api.getLastDaysManga().execute()
        val json = response.body()?.string()
        val contents = gson.fromJson(json, CategoriesContent::class.java)

        return makeMangaList(contents)
    }

    fun getBestVotedManga(): List<Manga> {
        val response = api.getBestVotedManga().execute()
        val json = response.body()?.string()
        val contents = gson.fromJson(json, CategoriesContent::class.java)

        return makeMangaList(contents)
    }

    fun getDailyTopManga(): List<Manga> {
        val response = api.getDailyTop().execute()
        val json = response.body()?.string()
        val contents = gson.fromJson(json, CategoriesContent::class.java)

        return makeMangaList(contents)
    }

    fun getNewChapters(): List<MangaNewChapter> {
        val response = api.getNewChapters().execute()
        val json = response.body()?.string()
        val contents = gson.fromJson(json, NewChapters::class.java)

        return makeNewChaptersList(contents)
    }


}