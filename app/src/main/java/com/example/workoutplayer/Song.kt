package com.example.workoutplayer

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaDataSource
import android.media.SoundPool
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object Song {
    private val SAMPLES = "intro outro pause rep"
    private var numLoadedSamples = 0

    var sampleQueue = LinkedBlockingQueue<SampleInfo>()
    var sampleList = ArrayList<SampleInfo>()
    private var soundPool = CustomSoundPool(1, AudioManager.STREAM_MUSIC, 0)
    private var soundMap = HashMap<String, Int>()

    init {
        soundPool.setOnLoadCompleteListener { _, _, _ -> numLoadedSamples += 1}
        //soundPool.onCompletionListener = { sampleQueue.remove() }
    }

    val isReady : Boolean
        get() = numLoadedSamples == SAMPLES.split(" ").size && !sampleQueue.isEmpty()

    fun loadSamples(context: Context) {
        if (isReady) return

        for (s: String in SAMPLES.split(" ")) {
            val id = context.resources.getIdentifier(s, "raw", "com.example.workoutplayer")
            soundMap[s] = soundPool.load(context, id)
        }
    }

    fun setSamples(list: ArrayList<SampleInfo>) {
        list.toCollection(sampleQueue)
        sampleList = list
    }

    fun play() {
        if (!isReady) return
        for (s in sampleList) {
            for (x in 1..s.repetitions) {
                soundPool.play(soundMap[s.title]!!, 0, s.rate)
            }
        }
    }
}
