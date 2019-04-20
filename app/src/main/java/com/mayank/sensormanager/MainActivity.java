package com.mayank.sensormanager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;

    private float acelvalue;  //  acceleration initial valuue
    private float acellast;   //  acceleration last  value
    private  float shake;     // change in acceleration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        sensorManager.registerListener(sensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

        acelvalue = SensorManager.GRAVITY_EARTH;
        acellast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            acellast = acelvalue;
            acelvalue = (float) Math.sqrt((double)(x*x + y*y + z*z));
            float delta = acelvalue - acellast;
            shake = shake + 0.9f +delta;


            if(shake >12){
                // any operation

                Toast.makeText(getApplicationContext(),"Hila mat Randi!!!!!",Toast.LENGTH_LONG).show();
            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
