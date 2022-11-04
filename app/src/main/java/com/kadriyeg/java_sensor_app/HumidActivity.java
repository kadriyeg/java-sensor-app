package com.kadriyeg.java_sensor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class HumidActivity extends AppCompatActivity implements SensorEventListener {
    private Sensor humidSensor;
    private TextView textView;
    private TextView highHumid;
    private TextView lowHumid;
    private SensorManager sensorManager;
    private Boolean isHumidSensAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humid);
        textView = findViewById(R.id.humidTextA);
        highHumid = findViewById(R.id.highHumid);
        lowHumid = findViewById(R.id.lowHumid);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humidSensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isHumidSensAvailable = true;
        } else {
            textView.setText("Humidity sensor is not available");
            isHumidSensAvailable = false;
            highHumid.setVisibility(TextView.GONE);
            lowHumid.setVisibility(TextView.GONE);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textView.setText(sensorEvent.values[0] + "% humidity");
        if (sensorEvent.values[0]>60){
            highHumid.setText("Weather humid level is high! You can take an umbrella");
            lowHumid.setVisibility(TextView.GONE);
        }
        if(sensorEvent.values[0]<30){
            highHumid.setVisibility(TextView.GONE);
            lowHumid.setText("Weather humid level is low, do not forget to apply some moisturizer");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isHumidSensAvailable) {
            sensorManager.registerListener(this,humidSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isHumidSensAvailable) {
            sensorManager.unregisterListener(this);
        }

    }
}