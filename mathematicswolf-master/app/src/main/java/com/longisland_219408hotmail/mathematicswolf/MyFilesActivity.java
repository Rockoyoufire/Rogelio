package com.longisland_219408hotmail.mathematicswolf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longisland_219408hotmail.mathematicswolf.adapter.ListAdapter;
import com.longisland_219408hotmail.mathematicswolf.db.DBManager;
import com.longisland_219408hotmail.mathematicswolf.model.Formulario;

import java.util.ArrayList;
import java.util.List;

public class MyFilesActivity extends AppCompatActivity {
    private ListView listView;
    private List<Formulario> formularioList = new ArrayList<>();
    private DBManager dbManager;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_files);
        dbManager = new DBManager(this);
        dbManager.open();
        listView = (ListView) findViewById(R.id.listview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        formularioList = dbManager.getFavList();
        listView.setAdapter(listAdapter = new ListAdapter(this, R.layout.item_formulario, formularioList) {
            @Override
            public void onEntrada(Object entrada, View view, int position) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imagen_single_post_circuito);
                TextView textView = (TextView) view.findViewById(R.id.tv_titulo_single_post_circuito);

                Glide.with(MyFilesActivity.this).load(formularioList.get(position).getImage()).into(imageView);
                textView.setText(formularioList.get(position).getName());

            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
