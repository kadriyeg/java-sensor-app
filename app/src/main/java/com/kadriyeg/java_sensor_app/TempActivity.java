package com.kadriyeg.java_sensor_app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TempActivity extends AppCompatActivity implements SensorEventListener {
    private Sensor tempSensor;
    private SensorManager sensorManager;
    private TextView textView;
    private TextView highTemp;
    private TextView lowTemp;
    private TextView warmTemp;
    private Boolean isTempSensAvaiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        textView = findViewById(R.id.temText);
        highTemp = findViewById(R.id.highTemp);
        lowTemp = findViewById(R.id.lowTemp);
        warmTemp = findViewById(R.id.warmTemp);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempSensAvaiable = true;
        } else {
            textView.setText("Temperature sensor is not available");
            isTempSensAvaiable = false;
            highTemp.setVisibility(TextView.GONE);
            lowTemp.setVisibility(TextView.GONE);
            warmTemp.setVisibility(TextView.GONE);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textView.setText(sensorEvent.values[0] + "°C");

        if (sensorEvent.values[0]>30.0){
            lowTemp.setVisibility(TextView.GONE);
            warmTemp.setVisibility(TextView.GONE);
            highTemp.setText("Weather will be hot!");
        }

        if((sensorEvent.values[0]>20.0) && (sensorEvent.values[0]<30.0)){
            highTemp.setVisibility(TextView.GONE);
            lowTemp.setVisibility(TextView.GONE);
            warmTemp.setText("It is warm today... Such a beautiful weather to hang out..");
        }

        if(sensorEvent.values[0]<20.0){
            highTemp.setVisibility(TextView.GONE);
            lowTemp.setText("It's cold! Dress well!");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTempSensAvaiable) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTempSensAvaiable) {
            sensorManager.unregisterListener(this);
        }

    }

}