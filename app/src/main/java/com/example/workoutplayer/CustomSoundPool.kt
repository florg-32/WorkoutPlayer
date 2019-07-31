package com.example.workoutplayer

import android.content.Context
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Handler
import android.util.SparseIntArray
import android.util.SparseLongArray
import java.lang.IllegalArgumentException

class CustomSoundPool(maxStreams: Int, streamType: Int, srcQuality: Int) :
    SoundPool(maxStreams, streamType, srcQuality) {

    var isPlaying: Boolean = false
        private set

    private var durationMap = SparseIntArray()

    private var currPlayId = 0
    private var timeSinceStart: Long = 0
    private var startTime: Long = 0

    private var handler = Handler()
    var onCompletionListener: ((Int) -> Unit)? = null

    fun load(context: Context, id: Int) : Int {
        val soundId = super.load(context, id, 1)
        durationMap.append(soundId, MediaPlayer.create(context, id).duration)
        return soundId
    }

    fun play(soundId: Int, loop: Int, rate: Float) {
        currPlayId = super.play(soundId, 1f, 1f, 1, loop, rate)
        if (currPlayId == 0) throw IllegalArgumentException("Playing sound with id $soundId not successful")

        isPlaying = true
        startTime = System.currentTimeMillis()
        handler.postDelayed({ onCompletionListener?.invoke(currPlayId) }, durationMap.get(soundId).toLong())
    }
}