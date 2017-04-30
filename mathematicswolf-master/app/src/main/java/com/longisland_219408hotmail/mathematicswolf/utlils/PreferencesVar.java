package com.longisland_219408hotmail.mathematicswolf.utlils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by edgararana on 25/04/17.
 */

public class PreferencesVar {
    //cadena que va a guardar el valor del permiso
    public static String permiss = "permiss";

    public Context context;

    public PreferencesVar(Context context) {
        this.context = context;
    }

    // TODO: 15/02/2017 metodos para los permisos de android 6
    public void setValuePermiss(Boolean valor) {
        SharedPreferences spPermiss = context.getSharedPreferences(permiss, MODE_PRIVATE);
        SharedPreferences.Editor editor = spPermiss.edit();
        editor.putBoolean("valor", valor);
        editor.apply();
    }

    public boolean getValuePermiss() {
        SharedPreferences recuperarValor = context.getSharedPreferences(permiss, MODE_PRIVATE);
        Boolean valorRecuperado = recuperarValor.getBoolean("valor", false);

        return valorRecuperado;
    }


}
