package com.example.workoutplayer

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        Song.loadSamples(this)
        val tmp = ArrayList<SampleInfo>()
        tmp.add(SampleInfo("intro", 2, 1f, Color.MAGENTA))
        tmp.add(SampleInfo("rep", 1, 2f, Color.YELLOW))
        Song.setSamples(tmp)

        play_button.setOnClickListener { Song.play() }
    }


}
