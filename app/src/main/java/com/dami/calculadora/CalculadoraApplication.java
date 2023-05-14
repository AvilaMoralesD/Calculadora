package com.dami.calculadora;

import android.app.Application;

import com.dami.calculadora.data.database.CalculadoraDatabase;

public class CalculadoraApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalculadoraDatabase.create(getApplicationContext());
    }
}
