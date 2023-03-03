package com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aut2_03aplicacinfinalandroid.R;

public class CargaFragment extends Fragment {
    private TextView tv;
    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int nivel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int escala = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float pt = nivel*100/(float) escala;
            tv.setText(String.valueOf(pt)+ "%");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_carga,container, false);
         tv = root.findViewById(R.id.textViewCarga);
         this.getActivity().registerReceiver(this.br,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return root;
    }
}