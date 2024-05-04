package com.example.lab4_salvador_anais;
import com.example.lab4_salvador_anais.databinding.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class SensorActivity extends AppCompatActivity{

    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(mSensorManager != null){ //validar si tengo sensores

            Sensor acelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if(acelerometer != null){ //validar un sensor en particular
                Toast.makeText(this, "Sí tiene acelerómetro", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Su equipo no dispone de acelerómetro",Toast.LENGTH_SHORT).show();
            }

            List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
            for(Sensor sensor : sensorList){
                Log.d("msg-test-sensorList","sensorName: " + sensor.getName());
            }

        }else{
            Toast.makeText(this, "Su dispositivo no posee sensores :(", Toast.LENGTH_SHORT).show();
        }
    }
}
