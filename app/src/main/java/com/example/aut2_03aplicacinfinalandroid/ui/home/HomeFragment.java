package com.example.aut2_03aplicacinfinalandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.aut2_03aplicacinfinalandroid.BD.Coche;
import com.example.aut2_03aplicacinfinalandroid.R;
import com.example.aut2_03aplicacinfinalandroid.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayList<String> coches = new ArrayList<String>();
        ArrayList<Coche> cochesbd = Coche.obtenerCoche(getContext());
        for (Coche coche:cochesbd) {
            coches.add(coche.getMatricula());
        }
        ListView listview = (ListView) root.findViewById(R.id.list_coches);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view, coches);
        listview.setAdapter(adapter);
        return root;
       /* HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}