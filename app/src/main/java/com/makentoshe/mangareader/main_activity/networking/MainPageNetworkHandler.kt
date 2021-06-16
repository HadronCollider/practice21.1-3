package com.makentoshe.mangareader.main_activity.networking

import android.accounts.NetworkErrorException
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.gson.Gson
import com.makentoshe.mangareader.api.StartPageApi
import com.makentoshe.mangareader.main_activity.networking.best_voted.BestVoted
import com.makentoshe.mangareader.main_activity.networking.best_voted.BestVotedManga
import com.makentoshe.mangareader.main_activity.networking.daily_top.DailyTop
import com.makentoshe.mangareader.main_activity.networking.daily_top.DailyTopManga
import com.makentoshe.mangareader.main_activity.networking.last_days_hot.LastDaysHot
import com.makentoshe.mangareader.main_activity.networking.last_days_hot.LastDaysHotManga
import com.makentoshe.mangareader.main_activity.networking.new_chapters.NewChapters
import com.makentoshe.mangareader.main_activity.networking.new_chapters.NewChaptersManga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class MainPageNetworkHandler(client: OkHttpClient, private val lifecycleCoroutineScope: LifecycleCoroutineScope) {
    private val gson = Gson()
    private var api: StartPageApi
    init{
        val retrofit = Retrofit.Builder().client(client).baseUrl("https://api.remanga.org/").build()
        api = retrofit.create(StartPageApi::class.java)
    }

    fun getBestVotedManga(after: (mangas: List<BestVotedManga>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getBestVotedManga().execute()

            if(!response.isSuccessful){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, BestVoted::class.java)

            lifecycleCoroutineScope.launch(Dispatchers.Main){
                after.invoke(contents.content)
            }
        }
    }

    fun getDailyTopManga(after: (mangas: List<DailyTopManga>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getDailyTop().execute()
            if(!response.isSuccessful){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, DailyTop::class.java)

            lifecycleCoroutineScope.launch(Dispatchers.Main){
                after.invoke(contents.content)
            }
        }
    }

    fun getLastDaysHotManga(after: (mangas: List<LastDaysHotManga>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO){
            val response = api.getLastDaysHotManga().execute()
            if(!response.isSuccessful){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, LastDaysHot::class.java)

            lifecycleCoroutineScope.launch(Dispatchers.Main){
                after.invoke(contents.content)
            }
        }
    }

    fun getNewChaptersManga(after: (mangas: List<NewChaptersManga>) -> Unit) {
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getNewChapters().execute()
            if(!response.isSuccessful){
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, NewChapters::class.java)

            lifecycleCoroutineScope.launch(Dispatchers.Main){
                after.invoke(contents.content)
            }
        }
    }


}