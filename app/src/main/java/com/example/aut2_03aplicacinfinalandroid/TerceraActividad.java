package com.example.aut2_03aplicacinfinalandroid;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;

import java.io.File;
import java.io.IOException;

public class TerceraActividad extends Activity {
    TextView tv1;
    MediaRecorder recorder;
    MediaPlayer player;
    private String archivo;
    Button b1, b2, b3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);

        tv1 = (TextView) this.findViewById(R.id.textView1);
        b1 = (Button) findViewById(R.id.btngrabar);
        b2 = (Button) findViewById(R.id.btndetener);
        b3 = (Button) findViewById(R.id.btnreproduccion);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grabar();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parar();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir();
            }
        });
    }

    private void parar(){
        this.recorder.stop();
        this.recorder.reset();
        this.recorder.release();
    }

    private void reproducir(){
        player = new MediaPlayer();
        try {
            this.player.setDataSource(this.archivo);
            this.player.prepare();
            this.player.start();
        }catch (IOException e){
            Log.e("grabar","Error al reproducir");
        }
    }

    private void grabar(){
        if(comprobarpermiso()){
           /* this.archivo = Environment.getExternalStorageDirectory().getAbsolutePath();*/
            this.archivo = getExternalFilesDir(null).getAbsolutePath();
            this.archivo += "/audio2.3gp";
            this.recorder = new MediaRecorder();
            this.recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            this.recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            this.recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            this.recorder.setOutputFile(this.archivo);
            try {
                this.recorder.prepare();
                this.recorder.start();
            }catch (IOException e) {
                e.printStackTrace();
                Log.e("grabar", "Error al grabar");
            }
        } else {
            pedirPermiso();
        }
    }

    private void pedirPermiso(){
        ActivityCompat.requestPermissions(TerceraActividad.this, new String[]{RECORD_AUDIO,WRITE_EXTERNAL_STORAGE},1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    boolean permisoGrabar = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permisoAmacenar = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(permisoGrabar && permisoAmacenar) {
                        Toast.makeText(getApplicationContext(), "Permiso concedido", Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }

    }

    private boolean comprobarpermiso(){
        int escritura = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int audio = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
         return escritura == PackageManager.PERMISSION_GRANTED && audio == PackageManager.PERMISSION_GRANTED;
    }
}
