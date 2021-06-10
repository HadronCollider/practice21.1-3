package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create list of items
        val Items = ArrayList<Chapter>()
        Items.add(Chapter("Chapter_1", "year: 2020", "1"))
        Items.add(Chapter("Chapter_2", "year: 2020", "2"))
        Items.add(Chapter("Chapter_3", "year: 2020", "3"))
        Items.add(Chapter("Chapter_4", "year: 2020", "4"))
        Items.add(Chapter("Chapter_5: End", "year: 2020", "5"))

        // create a recyclerView
        chapters.layoutManager = LinearLayoutManager(this)
        chapters.adapter = ChaptersAdapter(Items)
    }
}
