package com.makentoshe.mangareader.manga_main_page_activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.makentoshe.mangareader.R
import com.makentoshe.mangareader.main_activity.MangaForIntent
import com.makentoshe.mangareader.manga_main_page_activity.networking.MangaMainPageNetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.math.round

class ViewPagerAdapter(fragmentManager: FragmentManager, private val lifecycleCoroutineScope: LifecycleCoroutineScope,
                                  manga: MangaForIntent) : FragmentStatePagerAdapter(fragmentManager) {

    private val networkHandler = MangaMainPageNetworkHandler(OkHttpClient(), lifecycleCoroutineScope)

    private val mangaDir = manga.dir
    private val mangaId = manga.id
    private val chaptersCount: Int = manga.chaptersCount

    class DescriptionFragment(private val networkHandler: MangaMainPageNetworkHandler,
                              private val mangaDir: String,
                              private val lifecycleCoroutineScope: LifecycleCoroutineScope): Fragment(){
        private val client = OkHttpClient()

        private fun roundToThousands(number: Int): Int{
            return round(number.toDouble() / 1000.0).toInt()
        }

        private fun makeCorrectImageUrl(imageUrl : String): String{
            return "https://api.remanga.org${imageUrl}"
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            val view = inflater.inflate(R.layout.description_fragment, container, false)
            networkHandler.getMangaDescription(mangaDir){ description, similarManga ->
                view.findViewById<TextView>(R.id.descriptionTextView).text = description.description
                // Stats
                view.findViewById<TextView>(R.id.likesCount).text =
                    "${roundToThousands(description.totalVotes)}K лайков"
                view.findViewById<TextView>(R.id.viewsCount).text =
                    "${roundToThousands(description.totalViews)}K просмотров"
                view.findViewById<TextView>(R.id.bookmarksCount).text =
                    "${roundToThousands(description.countBookmarks)}K раз добавлено в закладки"

                //Genres
                description.genres.forEach { genre ->
                    val chip = inflater.inflate(R.layout.genre_chip, container, false) as Chip
                    chip.text = genre.name

                    view.findViewById<ChipGroup>(R.id.genresChipGroup).addView(chip)
                }

                //Publisher
                view.findViewById<TextView>(R.id.publisherName).text =
                    description.publishers[0].name
                view.findViewById<TextView>(R.id.publisherSlogan).text =
                    description.publishers[0].tagline
                lifecycleCoroutineScope.launch(Dispatchers.IO) {
                    val response =
                        client.newCall(Request.Builder().url(makeCorrectImageUrl(description.publishers[0].img)).build())
                            .execute()
                    val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                    lifecycleCoroutineScope.launch(Dispatchers.Main) {
                        view.findViewById<ImageView>(R.id.publisherAvatar).setImageBitmap(bitmap)
                    }
                }

                //Similar manga
                val similarMangaRecycler = view.findViewById<RecyclerView>(R.id.similarMangas)
                similarMangaRecycler.layoutManager =
                    LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
                similarMangaRecycler.adapter =
                    SimilarMangaRecyclerViewAdapter(similarManga, lifecycleCoroutineScope, client)
            }

            return view
        }
    }

    class ChaptersFragment(private val networkHandler: MangaMainPageNetworkHandler,
                           private val mangaDir: String/*,
                           private val lifecycleCoroutineScope: LifecycleCoroutineScope*/): Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val view =  inflater.inflate(R.layout.chapters_fragment, container, false)

            networkHandler.getMangaDescription(mangaDir){ description, _ ->
                //val activeBranch = if (description.branches.size == 1) description.branches[0].id else description.activeBranch
                networkHandler.getMangaChapters(description.branches[0].id){ chapters ->
                    val chaptersRecycler = view.findViewById<RecyclerView>(R.id.chapterListRecyclerView)
                    chaptersRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                    chaptersRecycler.adapter = ChaptersAdapter(chapters/*, lifecycleScope, OkHttpClient()*/)
                }
            }

            return view
        }
    }

    class CommentsFragment(private val networkHandler: MangaMainPageNetworkHandler,
                           private val mangaId: Int,/*,
                           private val lifecycleCoroutineScope: LifecycleCoroutineScope*/): Fragment(){

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val view: View  = inflater.inflate(R.layout.comments_fragment, container, false)
            val commentLayout = view.findViewById<EditText>(R.id.commentEditText) // get comment to post from here

            networkHandler.getComments(mangaId){comments ->
                view.findViewById<Button>(R.id.sendCommentButton).setOnClickListener{
                    Toast.makeText(view.context, "Ваш комментарий опубликован и появится чуть позже", Toast.LENGTH_SHORT).show()
                }

                val commentsView = view.findViewById<RecyclerView>(R.id.commentsRecyclerView)
                commentsView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                commentsView.adapter = CommentsAdapter(comments, lifecycleScope)
            }

            return view
        }
    }


    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DescriptionFragment(networkHandler, mangaDir, lifecycleCoroutineScope)
            1 -> ChaptersFragment(networkHandler, mangaDir/*, lifecycleCoroutineScope*/)
            2 -> CommentsFragment(networkHandler, mangaId/*, lifecycleCoroutineScope*/)
            else -> throw RuntimeException("ViewPager exception")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> "Описание"
            1 -> "Главы(${chaptersCount})"
            2-> "Комментарии"
            else -> throw RuntimeException("ViewPager exception")
        }
    }

}
