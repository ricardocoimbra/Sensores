package com.coimbra.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proximitySensor = findViewById(R.id.tvResultado);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager!=null) {
            Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if (proximitySensor!=null) {
                sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        } else {
            Toast.makeText(this, "Sensor não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY) {
            String sensorValue="Proximity Value: " + sensorEvent.values[0];
            proximitySensor.setText(sensorValue);
            if(sensorEvent.values[0] > 0) {
                Toast.makeText(this, "O objeto está longe", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "O objeto está próximo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}