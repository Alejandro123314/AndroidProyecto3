package com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class LocationTruck extends Service implements LocationListener {

    private Context c;
    private boolean cgps = false;
    private boolean cn = false;
    private boolean cl = false;
    Location loc;
    double lat;
    double log;
    private static long DISTANCE = 10;
    private static long UPDATE = 1000 * 60 * 1;
    protected LocationManager lm;


    public LocationTruck(Context context) {
        c = context;
        getLoc();
    }

    public void pararListener() {
        if(lm != null){
            lm.removeUpdates(LocationTruck.this);
        }
    }

    private Location getLoc() {
        lm = (LocationManager) c.getSystemService(LOCATION_SERVICE);
        cgps = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        cn = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!cn && !cgps) {
            Toast.makeText(c, "no disponible", Toast.LENGTH_LONG).show();
        } else {
            cl = true;
            if (cgps) {
                if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                }
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE, DISTANCE, this);
                if(lm != null){
                    loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(loc != null){
                        lat = loc.getLatitude();
                        log = loc.getLongitude();
                    }
                }
            }
        }
        return loc;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    public Context getC() {
        return c;
    }

    public boolean isCgps() {
        return cgps;
    }

    public boolean isCn() {
        return cn;
    }

    public boolean isCl() {
        return cl;
    }

    public double getLat() {
        return lat;
    }

    public double getLog() {
        return log;
    }

    public static long getDISTANCE() {
        return DISTANCE;
    }

    public static long getUPDATE() {
        return UPDATE;
    }

    public LocationManager getLm() {
        return lm;
    }
}
