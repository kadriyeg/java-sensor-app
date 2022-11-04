package com.kadriyeg.java_sensor_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.mainText);

        button = (Button) findViewById(R.id.humidButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHumidActivity();
            }
        });

        button = (Button) findViewById(R.id.tempButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTempActivity();
            }
        });
    }

    public void openHumidActivity(){
        Intent intent = new Intent(this, HumidActivity.class);
        startActivity(intent);
    }

    public void openTempActivity(){
        Intent intent = new Intent(this, TempActivity.class);
        startActivity(intent);
    }



}
