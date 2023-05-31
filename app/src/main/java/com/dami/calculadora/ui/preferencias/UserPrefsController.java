package com.dami.calculadora.ui.preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/*
 * Clase que gestiona la información del usuario en el fichero SharedPreferences
 * path: data/data/com.dami.calculadora/shared_preferences*/
public class UserPrefsController {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String KEY_FILENAME = "user_prefs";
    private static final String KEY_EMAIL = "email";

    public UserPrefsController(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY_FILENAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void login(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public boolean isUserLogged() {
        //El siguiente getString nos obliga un segundo parametro por defecto para que no devuelva un null
        return !sharedPreferences.getString(KEY_EMAIL, "").isEmpty();
    }

    public void logout() {
        editor.remove(KEY_EMAIL);
        editor.commit();
    }

    public String getUserEmail() { //Esto es equivalente a hacer un metodo que devuelva un token
        //La linea vacia es pa que nos devuelva algo aunque no este
        return sharedPreferences.getString(KEY_EMAIL, "");
    }
}

