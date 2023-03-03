package com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aut2_03aplicacinfinalandroid.R;


public class BrilloFragment extends Fragment {

    TextView tv;
    SensorManager sm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_brillo,container, false);
        tv = root.findViewById(R.id.textViewBrillo);
        sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor s = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(listener, s, SensorManager.SENSOR_DELAY_FASTEST);
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sm != null){
            sm.unregisterListener(listener);
        }
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Log.d("OK","ok");
            float valor = sensorEvent.values[0];
            if(valor < 100){
                tv.setText("oscuro");

            } else if(valor >= 100 && valor < 2000){
                tv.setText("normal");
            }else{
                tv.setText("brillante");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}