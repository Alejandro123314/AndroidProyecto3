package com.example.aut2_03aplicacinfinalandroid.ui.actualizar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.aut2_03aplicacinfinalandroid.BD.Coche;
import com.example.aut2_03aplicacinfinalandroid.R;
import com.example.aut2_03aplicacinfinalandroid.databinding.FragmentEliminarBinding;
import com.google.android.material.snackbar.Snackbar;

public class ActualizarFragment extends Fragment {

    private boolean ejecutar = false;
    private FragmentEliminarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEliminarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnGuardar = root.findViewById(R.id.buttonGuardar);
        Button btnEliminar = root.findViewById(R.id.buttonEliminar);
        EditText matriculaEliminar = root.findViewById(R.id.editTextTextMatriculaEliminar);
        EditText modeloEliminar = root.findViewById(R.id.editTextTextModeloEliminar);
        btnGuardar.setOnClickListener((View v)->{
            alerta();
            if(ejecutar == true){
                String m = matriculaEliminar.getText().toString();
                String mo = modeloEliminar.getText().toString();
                Coche coche = new Coche(mo, m , getContext());
                coche.actualizarCoche();
                matriculaEliminar.getText().clear();
                modeloEliminar.getText().clear();
                Snackbar snackbar = Snackbar.make(getContext(),root,"Se a actualizado un coche en la base de datos",4000);
                snackbar.show();
            }
            ejecutar = false;
        });
        btnEliminar.setOnClickListener((View v)->{
            alerta();
            if(ejecutar == true) {
                String m = matriculaEliminar.getText().toString();
                String mo = modeloEliminar.getText().toString();
                Coche coche = new Coche(mo, m, getContext());
                coche.eliminarCoche();
                matriculaEliminar.getText().clear();
                modeloEliminar.getText().clear();
                Snackbar snackbar = Snackbar.make(getContext(),root,"Se a eliminado un coche en la base de datos",4000);
                snackbar.show();
            }
            ejecutar = false;
        });
        return root;

    }

    private void alerta(){
        AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
        ad.setTitle("Confirmacion");
        ad.setMessage("Quieres condirmar este cambio");
        ad.setCancelable(false);
        ad.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ejecutar = true;
                Log.d("confirmar", new Boolean(ejecutar).toString());
            }
        });
        ad.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ejecutar = false;
                Log.d("cancelar", new Boolean(ejecutar).toString());
            }
        });
        ad.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}