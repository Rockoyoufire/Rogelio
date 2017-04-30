package com.longisland_219408hotmail.mathematicswolf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.longisland_219408hotmail.mathematicswolf.adapter.GalleryAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Gallery gallery;
    private GalleryAdapter galleryAdapter;

    private LinearLayout llTomar, llFille, llAcerca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gallery = (Gallery) findViewById(R.id.fu_galery);
        llTomar = (LinearLayout) findViewById(R.id.ll_tomar);
        llFille = (LinearLayout) findViewById(R.id.ll_archivo);
        llAcerca = (LinearLayout) findViewById(R.id.ll_acerca);


        llTomar.setOnClickListener(this);
        llFille.setOnClickListener(this);
        llAcerca.setOnClickListener(this);
        final int topics[] = {
                R.drawable.derivadas,
                R.drawable.ecuaciones,
                R.drawable.estadistica,
                R.drawable.integrales,
        };

        gallery.setAdapter(galleryAdapter = new GalleryAdapter(this, R.layout.item_garllery, topics) {
            @Override
            public void onEntrada(Object entrada, View view, int position) {
                ImageView imageView = (ImageView) view.findViewById(R.id.item_image);

                Glide.with(MainActivity.this).load(topics[position]).into(imageView);

            }

        });

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("item", i + "");
                Intent intentList = new Intent(MainActivity.this, ListActivity.class);
                intentList.putExtra("type", i);
                startActivity(intentList);
            }
        });

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_tomar:
                Intent iTomar = new Intent(MainActivity.this, ImageSelectActivity.class);
                startActivity(iTomar);
                break;
            case R.id.ll_archivo:
                Intent iFile = new Intent(MainActivity.this, MyFilesActivity.class);
                startActivity(iFile);
                break;
            case R.id.ll_acerca:
                Intent iAcerca = new Intent(MainActivity.this, AcercaActivity.class);
                startActivity(iAcerca);
                break;
        }

    }

    public void onEvent(View view) {
        switch (view.getId()){
        case R.id.bnt_tomar:
        Intent iTomar = new Intent(MainActivity.this, ImageSelectActivity.class);
        startActivity(iTomar);
        break;
        case R.id.bnt_archivo:
        Intent iFile = new Intent(MainActivity.this, MyFilesActivity.class);
        startActivity(iFile);
        break;
        case R.id.bnt_acerca:
        Intent iAcerca = new Intent(MainActivity.this, AcercaActivity.class);
        startActivity(iAcerca);
        break;
    }
}
}
