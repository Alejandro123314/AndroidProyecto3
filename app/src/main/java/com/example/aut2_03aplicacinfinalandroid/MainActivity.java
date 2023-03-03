package com.example.aut2_03aplicacinfinalandroid;






import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.aut2_03aplicacinfinalandroid.ui.gallery.GalleryFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aut2_03aplicacinfinalandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment cf = new GalleryFragment();
                FragmentManager fm =getSupportFragmentManager();
                FragmentTransaction ft =fm.beginTransaction();
                ft.replace( R.id.nav_host_fragment_content_main,cf).commit();
                //ft.addToBackStack(null);
              //  ft.commit();
               // getSupportFragmentManager().beginTransaction().replace(binding.getRoot().getId(),cf).commit();
               /* FragmentTransaction cft = getFragmentManager().beginTransaction();
                ((FragmentTransaction) cft).replace(R.id.nav_gallery, cf);*/
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_activity2:
                Intent intent = new Intent(this, SegundaActividad.class);
                startActivity(intent);
                return true;
            case R.id.action_activity3:
                Intent intent3 = new Intent(this, TerceraActividad.class);
                startActivity(intent3);
                return true;
            case R.id.action_activity4:
                Intent intent4 = new Intent(this, CuartaActividad.class);
                startActivity(intent4);
                return true;
            case R.id.acercade:
                Intent intent5 = new Intent(this, Acerde.class);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}