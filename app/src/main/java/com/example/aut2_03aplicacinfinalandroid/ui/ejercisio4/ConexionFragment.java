package com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aut2_03aplicacinfinalandroid.R;

import java.util.ArrayList;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class ConexionFragment extends Fragment {

Button b;
ArrayList<String> permisosRequeridos;
ArrayList<String> permisosDenegados = new ArrayList();
ArrayList permisos = new ArrayList();
LocationTruck lt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_conexion,container, false);
        b = root.findViewById(R.id.btnGps);

        permisos.add(ACCESS_FINE_LOCATION);
        permisos.add(ACCESS_COARSE_LOCATION);
        permisosRequeridos = buscarPermiso(permisos);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(permisosRequeridos.size() > 0){
                requestPermissions(permisosRequeridos.toArray(new String[permisosRequeridos.size()]), 101);
            }
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lt = new LocationTruck(getContext());
                if (lt.isCgps()){
                    double longitud = lt.getLog();
                    double latitud = lt.getLat();
                    Toast.makeText(getContext(), "longitud: " + longitud + " latitud " + latitud, Toast.LENGTH_LONG).show();
                } else{
                    Log.e("1", "no puede");
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lt.pararListener();
    }

    private ArrayList buscarPermiso(ArrayList<String> p){
        ArrayList result = new ArrayList();
        for (String pm: p) {
            if (!tienePermiso(pm)){
                result.add(pm);
            }
        }
        return result;
    }

    private boolean tienePermiso(String p){
        if (comprobarSdk()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                return getActivity().checkSelfPermission(p) == PackageManager.PERMISSION_GRANTED;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case 101:
               for (String p:permisosRequeridos) {
                   if (!tienePermiso(p)){
                       permisosDenegados.add(p);
                   }
               }
               if (permisosDenegados.size() > 0){
                   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                       if(shouldShowRequestPermissionRationale(permisosDenegados.get(0))){
                            mostrarPermisos("permisos requeridos", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                        requestPermissions(permisosDenegados.toArray(new String[permisosDenegados.size()]), 101);
                                    }
                                }
                            });
                       }
                   }
               }
       }
    }

    private void mostrarPermisos(String mensaje, DialogInterface.OnClickListener ok){
        new AlertDialog.Builder(getContext()).setMessage(mensaje).setPositiveButton("ok", ok).setNegativeButton("cancelar", null).create().show();
    }

    private boolean comprobarSdk(){
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }


}