package com.makentoshe.mangareader.manga_main_page_activity.networking

import android.accounts.NetworkErrorException
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.gson.Gson
import com.makentoshe.mangareader.api.MangaMainPageApi
import com.makentoshe.mangareader.manga_main_page_activity.networking.chapters.ChaptersContent
import com.makentoshe.mangareader.manga_main_page_activity.networking.chapters.MangaChapters
import com.makentoshe.mangareader.manga_main_page_activity.networking.comments.CommentsContent
import com.makentoshe.mangareader.manga_main_page_activity.networking.comments.MangaComments
import com.makentoshe.mangareader.manga_main_page_activity.networking.description.DescriptionContent
import com.makentoshe.mangareader.manga_main_page_activity.networking.description.MangaDescription
import com.makentoshe.mangareader.manga_main_page_activity.networking.similar_mangas.SimilarMangaContent
import com.makentoshe.mangareader.manga_main_page_activity.networking.similar_mangas.SimilarMangas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class MangaMainPageNetworkHandler(client: OkHttpClient,
                                  private val lifecycleCoroutineScope: LifecycleCoroutineScope) {
    private val gson = Gson()
    private var api: MangaMainPageApi
    init{
        val retrofit = Retrofit.Builder().client(client).baseUrl("https://api.remanga.org/").build()
        api = retrofit.create(MangaMainPageApi::class.java)
    }

    fun getMangaDescription(manga_dir: String, after: (description: DescriptionContent, similarManga: List<SimilarMangaContent>) -> Unit){
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val descriptionResponse = api.getMangaDescription(manga_dir).execute()

            if (!descriptionResponse.isSuccessful) {
                throw NetworkErrorException("Request failed: status code: ${descriptionResponse.code()}")
            }

            val similarMangaResponse = api.getSimilarManga(manga_dir).execute()

            if (!similarMangaResponse.isSuccessful) {
                throw NetworkErrorException("Request failed: status code: ${similarMangaResponse.code()}")
            }


            val descriptionJson = descriptionResponse.body()?.string()
            val descriptionContents = gson.fromJson(descriptionJson, MangaDescription::class.java)

            val similarMangaJson = similarMangaResponse.body()?.string()
            val similarMangaContents = gson.fromJson(similarMangaJson, SimilarMangas::class.java)

            lifecycleCoroutineScope.launch(Dispatchers.Main) {
                after.invoke(descriptionContents.content, similarMangaContents.content)
            }
        }
    }

    fun getMangaChapters(branchId: Int, after: (chapters: List<ChaptersContent>) -> Unit){
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getChapters(branchId).execute()

            if (!response.isSuccessful) {
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, MangaChapters::class.java)

            lifecycleCoroutineScope.launch(Dispatchers.Main) {
                after.invoke(contents.content)
            }
        }
    }

    fun getComments(mangaId: Int, after: (comments: List<CommentsContent>) -> Unit){
        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = api.getComments(mangaId).execute()

            if (!response.isSuccessful) {
                throw NetworkErrorException("Request failed: status code: ${response.code()}")
            }

            val json = response.body()?.string()
            val contents = gson.fromJson(json, MangaComments::class.java)

            lifecycleCoroutineScope.launch(Dispatchers.Main) {
                after.invoke(contents.content)
            }
        }
    }
}