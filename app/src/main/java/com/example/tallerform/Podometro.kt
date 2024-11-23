package com.example.tallerform
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Podometro : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private var stepCount = 0
    private lateinit var btnIniciar: Button
    private lateinit var btnParar: Button
    private lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podometro)


        btnIniciar = findViewById(R.id.btnIniciar)
        btnParar = findViewById(R.id.btnParar)
        textView2 = findViewById(R.id.textView2)


        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)


        btnIniciar.setOnClickListener {
            stepCount = 0
            textView2.text = "Contando pasos"
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }

        btnParar.setOnClickListener {
            sensorManager.unregisterListener(this)
            textView2.text = "Pasos contados: $stepCount"
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {

            if (event.values[0] == 1f) {
                stepCount++
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}
