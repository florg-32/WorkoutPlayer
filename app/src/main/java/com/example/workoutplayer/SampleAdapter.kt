package com.example.workoutplayer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SampleAdapter(var sampleList: List<SampleInfo>, val context : Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_view, p0, false))
    }

    override fun getItemCount(): Int {
        return sampleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.sampleView as SampleView
        view.setSample(sampleList[position])
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val sampleView = view
}