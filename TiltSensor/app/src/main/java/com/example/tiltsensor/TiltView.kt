package com.example.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        greenPaint.color = Color.GREEN
        blackPaint.color = Color.BLACK
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f
        cY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        // 바깥 원
        canvas?.drawCircle(cX, cY, 100f, blackPaint)
        // 녹색 원
        canvas?.drawCircle(xCoord + cX, yCoord + cY, 100f, greenPaint)
        // 가운데 십자가
        canvas?.drawLine(cX - 20, cY, cX + 30, cY, blackPaint)
        canvas?.drawLine(cX, cY - 20, cX, cY + 20, blackPaint)
    }

    fun onSensorEvent(event: SensorEvent) {
        // 화면을 가로로 돌렸으니 X 축과 Y 축을 서로 바꿈
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20
        invalidate() // ---> onDraw 메소드를 다시 호ㅜㄹ하는 메소드. -> 뷰를 다시 그림
    }
}