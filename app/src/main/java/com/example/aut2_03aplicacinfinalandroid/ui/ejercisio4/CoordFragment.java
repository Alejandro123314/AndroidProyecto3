package com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aut2_03aplicacinfinalandroid.R;

public class CoordFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_coordenada,container, false);
        TextView tv = root.findViewById(R.id.textViewCordenadas);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int alto = dm.heightPixels;
        int ancho = dm.widthPixels;
        tv.setText("Ancho " + ancho + " alto " + alto);
        return root;
    }
}
