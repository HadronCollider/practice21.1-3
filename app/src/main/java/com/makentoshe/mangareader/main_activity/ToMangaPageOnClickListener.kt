package com.makentoshe.mangareader.main_activity

import android.content.Intent
import android.view.View
import com.makentoshe.mangareader.manga_main_page_activity.MangaMainPage

class ToMangaPageOnClickListener(private val manga: MangaForIntent): View.OnClickListener {
    override fun onClick(v: View?) {
        val intent = Intent(v?.context, MangaMainPage::class.java)
        intent.putExtra("manga", manga)

        v?.context?.startActivity(intent)
    }
}