package com.example.aut2_03aplicacinfinalandroid;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SegundaActividad extends Activity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    private static final String[] myDataSet = {
            "PHP",
            "Javascript",
            "Go",
            "Python"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        // Asociamos un adapter (ver más adelante cómo definirlo)
        mAdapter = new MyAdapter(myDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }

    // ...
}

