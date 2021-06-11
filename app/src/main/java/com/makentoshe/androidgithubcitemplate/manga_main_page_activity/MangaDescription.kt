package com.makentoshe.androidgithubcitemplate.manga_main_page_activity

import com.makentoshe.androidgithubcitemplate.main_activity.Manga

data class MangaDescription(val stats: MangaStatistics, val description: String,
                            val genres: List<String>, val publisher: Publisher, val similarMangas: List<Manga>)