package com.example.workoutplayer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SampleView : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var color = Color.GRAY
    private var title = "Empty"
    private var reps = 0

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        paint.textSize = 36f
        paint.textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = color
        paint.style = Paint.Style.FILL
        canvas.drawPaint(paint)

        paint.color = Color.BLACK
        canvas.drawText(title, width/2f, height / 2f - 5, paint)
        canvas.drawText("x$reps", width/2f, height / 2 + paint.fontSpacing + 5, paint)
    }

    fun setSample(s: SampleInfo) {
        color = s.color
        title = s.title
        reps = s.repetitions
    }
}

