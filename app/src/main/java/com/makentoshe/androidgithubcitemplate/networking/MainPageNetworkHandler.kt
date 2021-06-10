package com.makentoshe.androidgithubcitemplate.networking

import android.accounts.NetworkErrorException
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.gson.Gson
import com.makentoshe.androidgithubcitemplate.api.MainScreenAPI
import com.makentoshe.androidgithubcitemplate.main_activity.Manga
import com.makentoshe.androidgithubcitemplate.main_activity.MangaNewChapter
import com.makentoshe.androidgithubcitemplate.networking.best_voted.BestVoted
import com.makentoshe.androidgithubcitemplate.networking.daily_top.DailyTop
import com.makentoshe.androidgithubcitemplate.networking.last_days_hot.LastDaysHot
import com.makentoshe.androidgithubcitemplate.networking.new_chapters.NewChapters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit

class MainPageNetworkHandler(client: OkHttpClient, private val lifecycleCoroutineScope: LifecycleCoroutineScope) {
    private val gson = Gson()
    private var api: MainScreenAPI
    init{
        val retrofit = Retrofit.Builder().client(client).baseUrl("https://api.remanga.org/").build()
        api = retrofit.create(MainScreenAPI::class.java)
    }

    private fun checkStatusCode(response:  Response<ResponseBody>): Boolean{
        return response.code() == 200
    }

    fun getBestVotedManga(after: (mangas: List<Manga>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getBestVotedManga().execute()

            if(!checkStatusCode(response)){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, BestVoted::class.java)

            val result = contents.content.map { content ->
                Manga(content.rusName, content.genres[0].name, makeCorrectImageUrl(content.img.high))
            }

            lifecycleCoroutineScope.launch(Dispatchers.Main){
                after.invoke(result)
            }
        }
    }

    fun getDailyTopManga(after: (mangas: List<Manga>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getDailyTop().execute()
            if(!checkStatusCode(response)){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, DailyTop::class.java)

            val result = contents.content.map { content ->
                Manga(content.rusName, "${content.type} ${content.issueYear}", makeCorrectImageUrl(content.img.high))
            }

            lifecycleCoroutineScope.launch(Dispatchers.Main) {
                after.invoke(result)
            }
        }
    }

    fun getLastDaysHotManga(after: (mangas: List<Manga>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO){
            val response = api.getLastDaysHotManga().execute()
            if(!checkStatusCode(response)){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, LastDaysHot::class.java)

            val result = contents.content.map { content ->
                Manga(content.rusName, "${content.type} ${content.genres[0].name}", makeCorrectImageUrl(content.img.high))
            }

            lifecycleCoroutineScope.launch(Dispatchers.Main){
                after.invoke(result)
            }
        }
    }

    fun getNewChapters(after: (mangas: List<MangaNewChapter>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getNewChapters().execute()
            if(!checkStatusCode(response)){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, NewChapters::class.java)

            val result = contents.content.map { content ->
                MangaNewChapter(content.rusName, "Tome ${content.tome}. Chapter ${content.chapter}", content.uploadDate, makeCorrectImageUrl(content.img.high))
            }

            lifecycleCoroutineScope.launch(Dispatchers.Main) {
                after.invoke(result)
            }
        }
    }

    private fun makeCorrectImageUrl(imageUrl : String): String{
        return "https://api.remanga.org${imageUrl}"
    }


}