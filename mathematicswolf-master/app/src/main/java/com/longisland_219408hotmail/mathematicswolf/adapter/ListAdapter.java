package com.longisland_219408hotmail.mathematicswolf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.longisland_219408hotmail.mathematicswolf.model.MathematicsWolf;

import java.util.List;

/**
 * Created by edgararana on 25/04/17.
 */

public abstract class ListAdapter extends BaseAdapter {
    private List<?> entradas;
    private Context context;
    private int R_layout_IdView;


    public ListAdapter(Context contexto, int R_layout_IdView, List<?> entradas) {
        super();
        this.context = contexto;
        this.entradas = entradas;
        this.R_layout_IdView = R_layout_IdView;


    }

    @Override
    public View getView(final int posicion, View view, final ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = vi.inflate(R_layout_IdView, null);


        }

        onEntrada(entradas.get(posicion), view, posicion);
        return view;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }


    @Override
    public Object getItem(int posicion) {
        return entradas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    public void removeView(Object position) {
        // lv and the adapter must be public-static in their Activity Class

    }

    public abstract void onEntrada(Object entrada, View view, int position);
}
