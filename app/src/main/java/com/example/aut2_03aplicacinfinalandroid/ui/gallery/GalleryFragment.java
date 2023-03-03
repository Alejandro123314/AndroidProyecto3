package com.example.aut2_03aplicacinfinalandroid.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aut2_03aplicacinfinalandroid.BD.Coche;
import com.example.aut2_03aplicacinfinalandroid.R;
import com.example.aut2_03aplicacinfinalandroid.databinding.FragmentGalleryBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btn1 = root.findViewById(R.id.buttonCrear);
        EditText matricula = root.findViewById(R.id.editTextTextMatricula);
        EditText modelo = root.findViewById(R.id.editTextTextModelo);
        btn1.setOnClickListener((View v)->{
            String m = matricula.getText().toString();
            String mo = modelo.getText().toString();
            Coche coche = new Coche(mo, m , getContext());
            coche.guardarCoche();
            matricula.getText().clear();
            modelo.getText().clear();
            Snackbar snackbar = Snackbar.make(getContext(),root,"Se a creado un coche en la base de datos",4000);
            snackbar.show();
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}