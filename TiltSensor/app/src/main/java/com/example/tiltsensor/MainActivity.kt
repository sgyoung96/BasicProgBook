package com.example.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val SENSOR_DELAY_NORMAL = SensorManager.SENSOR_DELAY_NORMAL

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면이 꺼지지 않게
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 화면을 가로모드로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SENSOR_DELAY_NORMAL)
    }

    /**
     * 센서값이 변경되면 호출됨
     * values[0] : x 값 : 위로 기울이면 -10 ~ 0, 아래로 기울이면 0 ~ 10
     * values[1] : y 값 : 왼쪽으로 기울이면 -10 ~ 0, 오른쪽으로 기울이면 0 ~ 10
     * values[2] : z 값 : 미사용
     */
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.d("MainActivity", "OnSensorChanged : x : " + "${event.values[0]}, y : ${event.values[1]}, ${event.values[2]}")

            tiltView.onSensorEvent(event)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}