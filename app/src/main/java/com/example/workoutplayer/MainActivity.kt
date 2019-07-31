package com.example.workoutplayer

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Song.loadSamples(this)

        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val tmp = ArrayList<SampleInfo>()
        tmp.add(SampleInfo("intro", 2, 1f, Color.MAGENTA))
        tmp.add(SampleInfo("rep", 1, 2f, Color.YELLOW))
        viewAdapter = SampleAdapter(tmp, this)
        Song.setSamples(tmp)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
