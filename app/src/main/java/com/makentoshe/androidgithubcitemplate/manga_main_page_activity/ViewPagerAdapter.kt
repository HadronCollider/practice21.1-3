package com.makentoshe.androidgithubcitemplate.manga_main_page_activity

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
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.main_activity.BestVotedRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class ViewPagerAdapter(fragmentManager: FragmentManager, private val lifecycleCoroutineScope: LifecycleCoroutineScope,
                       private val description: MangaDescription, private val chapters: List<Chapter>,
                       private val comments: List<String>) : FragmentStatePagerAdapter(fragmentManager) {

    class DescriptionFragment(private val description: MangaDescription,
                              private val lifecycleCoroutineScope: LifecycleCoroutineScope): Fragment(){
        private val client = OkHttpClient()

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.description_fragment, container, false)

            view.findViewById<TextView>(R.id.descriptionTextView).text = description.description
            // Stats
            view.findViewById<TextView>(R.id.likesCount).text = "${description.stats.likes} likes"
            view.findViewById<TextView>(R.id.viewsCount).text = "${description.stats.views} views"
            view.findViewById<TextView>(R.id.bookmarksCount).text = "${description.stats.bookmarks} bookmarks"

            //Genres
            description.genres.forEach { genre ->
                val chip = inflater.inflate(R.layout.genre_chip, container, false) as Chip
                chip.text = genre

                view.findViewById<ChipGroup>(R.id.genresChipGroup).addView(chip)
            }

            //Publisher
            view.findViewById<TextView>(R.id.publisherName).text = description.publisher.publisherName
            view.findViewById<TextView>(R.id.publisherSlogan).text = description.publisher.publisherSlogan
            lifecycleCoroutineScope.launch(Dispatchers.IO) {
                val response = client.newCall(Request.Builder().url(description.publisher.publisherAvatarUrl).build()).execute()
                val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                lifecycleCoroutineScope.launch(Dispatchers.Main) {
                    view.findViewById<ImageView>(R.id.publisherAvatar).setImageBitmap(bitmap)
                }
            }

            //Similar manga
            val similarMangaRecycler = view.findViewById<RecyclerView>(R.id.similarMangas)
            similarMangaRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            similarMangaRecycler.adapter = BestVotedRecyclerViewAdapter(description.similarMangas, lifecycleCoroutineScope, client)

            return view
        }
    }

    class ChaptersFragment(private val chapters: List<Chapter>): Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view =  inflater.inflate(R.layout.chapters_fragment, container, false)

            val chaptersRecycler = view.findViewById<RecyclerView>(R.id.chapterListRecyclerView)
            chaptersRecycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            chaptersRecycler.adapter = ChaptersAdapter(chapters, lifecycleScope, OkHttpClient())

            return view
        }
    }

    class CommentsFragment(private val comments: List<String>) : Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view: View  = inflater.inflate(R.layout.comments_fragment, container, false)
            val commentLayout = view.findViewById<EditText>(R.id.commentEditText) // get comment to post from here

            view.findViewById<Button>(R.id.sendCommentButton).setOnClickListener{
                Toast.makeText(view.context, "Ваш комментарий опубликован и появится чуть позже", Toast.LENGTH_SHORT).show()
            }

            val commentsView = view.findViewById<RecyclerView>(R.id.commentsRecyclerView)
            commentsView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            commentsView.adapter = CommentsAdapter(comments)

            return view
        }
    }


    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DescriptionFragment(description, lifecycleCoroutineScope)
            1 -> ChaptersFragment(chapters)
            2 -> CommentsFragment(comments)
            else -> throw RuntimeException("ViewPager exception")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Описание"
            1 -> "Главы(${chapters.size})"
            2-> "Комментарии"
            else -> throw RuntimeException("ViewPager exception")
        }
    }
}
