package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create list of items
        val Items = ArrayList<Chapter>()
        Items.add(Chapter("Chapter_1", "year: 2020", R.drawable.ic_launcher_foreground))

        // create a listView and ArrayAdapter
        val adapter = ChaptersAdapter(this, R.layout.row, Items)
        chapters.adapter = adapter
    }
}
