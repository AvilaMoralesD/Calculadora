package com.dami.calculadora.ui.fragment;

import android.app.Application;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.dami.calculadora.CalculadoraApplication;
import com.dami.calculadora.R;
import com.dami.calculadora.databinding.FragmentFirstBinding;
import com.dami.calculadora.ui.base.BaseFragment;
import com.dami.calculadora.ui.preferencias.UserPrefsController;
import com.dami.calculadora.ui.viewmodel.OperacionesViewmodel;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class FirstFragment extends BaseFragment {

    private FragmentFirstBinding binding;
    private OperacionesViewmodel viewmodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        viewmodel = new ViewModelProvider(this).get(OperacionesViewmodel.class);
        setHasOptionsMenu(true);
        //Para actualizar el índice solamente
        viewmodel.load();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnMas.setOnClickListener(v -> viewmodel.tryToOperate(binding.tiePrimero.getText().toString(), getString(R.string.plus), binding.tieSegundo.getText().toString()));
        binding.btnMenos.setOnClickListener(v -> viewmodel.tryToOperate(binding.tiePrimero.getText().toString(), getString(R.string.minus), binding.tieSegundo.getText().toString()));
        binding.btnMultiplicar.setOnClickListener(v -> viewmodel.tryToOperate(binding.tiePrimero.getText().toString(), getString(R.string.multiplied), binding.tieSegundo.getText().toString()));
        binding.btnDividir.setOnClickListener(v -> viewmodel.tryToOperate(binding.tiePrimero.getText().toString(), getString(R.string.divided), binding.tieSegundo.getText().toString()));

        binding.tiePrimero.addTextChangedListener(new MyTextWatcher(binding.tilPrimero));
        binding.tieSegundo.addTextChangedListener(new MyTextWatcher(binding.tilSegundo));

        viewmodel.getResult().observe(getViewLifecycleOwner(), operacionResult ->
        {
            switch (operacionResult) {
                case NUM1EMPTY:
                    binding.tilPrimero.setError(getString(R.string.num_vacio));
                    break;
                case NUM2EMPTY:
                    binding.tilSegundo.setError(getString(R.string.num_vacio));
                    break;
                case DIVIDIR_POR_CERO:
                    binding.tilSegundo.setError(getString(R.string.no_puedes_dividir_entre_cero));
                    break;
                case SUCCESS:
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.format(viewmodel.getData().getResultado());
                    binding.tvResultado.setText(df.format(viewmodel.getData().getResultado()));
                    showNotification(String.valueOf(viewmodel.getData().getResultado()), R.id.FirstFragment, CalculadoraApplication.CHANNEL_ID);

                    break;
            }
        });
        //Comprobar si el usuario esta logueado
        switchOperations(new UserPrefsController(getContext()).isUserLogged());
    }

    private void switchOperations(boolean b) {
        binding.btnMas.setEnabled(b);
        binding.btnMenos.setEnabled(b);
        binding.btnDividir.setEnabled(b);
        binding.btnMultiplicar.setEnabled(b);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_primero, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_basura) {
            binding.tiePrimero.setText("");
            binding.tieSegundo.setText("");
            return true;
        }
        if (id == R.id.action_navegar_siguiente) {
            NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class MyTextWatcher implements TextWatcher {
        private TextInputLayout til;

        MyTextWatcher(TextInputLayout TextInputLayout) {
            til = TextInputLayout;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            til.setError("");
        }
    }

}