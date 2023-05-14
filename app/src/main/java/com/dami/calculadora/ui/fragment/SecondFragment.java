package com.dami.calculadora.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.dami.calculadora.R;
import com.dami.calculadora.data.adapter.OperacionAdapter;
import com.dami.calculadora.databinding.FragmentSecondBinding;
import com.dami.calculadora.ui.viewmodel.ListaViewmodel;

public class SecondFragment extends Fragment {

    private OperacionAdapter adapter;
    private ListaViewmodel viewmodel;
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewmodel = new ViewModelProvider(this).get(ListaViewmodel.class);
        viewmodel.load();
        adapter = new OperacionAdapter(operacion -> Toast.makeText(getContext(), operacion.toString(), Toast.LENGTH_SHORT).show());
        binding.miRecycler.setAdapter(adapter);
        viewmodel.getData().observe(getViewLifecycleOwner(), listaLeads -> adapter.update(listaLeads));
        viewmodel.getResult().observe(getViewLifecycleOwner(), leadsListResult -> {
                    switch (leadsListResult) {
                        case NODATA:
                            binding.tvNodata.setVisibility(View.VISIBLE);
                            binding.miRecycler.setVisibility(View.GONE);
                            break;
                        case SUCCESS:
                            binding.tvNodata.setVisibility(View.GONE);
                            binding.miRecycler.setVisibility(View.VISIBLE);
                            break;
                    }
                }
        );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_ordenar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_ordenarPorId) {
            adapter.ordenarPorTipo();
            return true;
        }
        if (id == R.id.action_ordenarCronologico) {
            adapter.ordenarPorID();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}