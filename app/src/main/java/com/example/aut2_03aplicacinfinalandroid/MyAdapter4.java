package com.example.aut2_03aplicacinfinalandroid;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4.BrilloFragment;
import com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4.CargaFragment;
import com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4.ConexionFragment;
import com.example.aut2_03aplicacinfinalandroid.ui.ejercisio4.CoordFragment;

public class MyAdapter4 extends FragmentStateAdapter {

    public MyAdapter4(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CoordFragment();
            case 1:
                return new CargaFragment();
            case 2:
                return new BrilloFragment();
            case 3:
                return new ConexionFragment();
            default:
                return new CoordFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
