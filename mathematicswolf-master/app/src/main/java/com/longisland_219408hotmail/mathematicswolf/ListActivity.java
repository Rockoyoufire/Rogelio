package com.longisland_219408hotmail.mathematicswolf;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longisland_219408hotmail.mathematicswolf.Bean.Global;
import com.longisland_219408hotmail.mathematicswolf.adapter.GalleryAdapter;
import com.longisland_219408hotmail.mathematicswolf.adapter.ListAdapter;
import com.longisland_219408hotmail.mathematicswolf.model.MathematicsWolf;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private int typeList;
    private List<MathematicsWolf> mathematicsWolfList = new ArrayList<>();
    private ListView listView;
    private ListAdapter listAdapter;
    private String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().getExtras() != null) {
            typeList = getIntent().getExtras().getInt("type");
            mathematicsWolfList = getList(typeList);
            listView.setAdapter(listAdapter = new ListAdapter(this, R.layout.item_list, mathematicsWolfList) {
                @Override
                public void onEntrada(Object entrada, View view, int position) {
                    ImageView imageView = (ImageView) view.findViewById(R.id.imagen_single_post_circuito);
                    TextView textView = (TextView) view.findViewById(R.id.tv_titulo_single_post_circuito);

                    Glide.with(ListActivity.this).load(mathematicsWolfList.get(position).getImage()).into(imageView);
                    textView.setText(mathematicsWolfList.get(position).getName());

                }

            });
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListActivity.this, DetailsListActivity.class);
                intent.putExtra("type", i);
                intent.putExtra("menu", menu);
                startActivity(intent);

            }
        });

    }


    List<MathematicsWolf> getList(int type) {
        List<MathematicsWolf> genericList = new ArrayList<>();

        switch (type) {
            case Global.derivadas:
                menu = Global.derivadass;
                genericList.add(new
                        MathematicsWolf("Derivadas", R.drawable.derivadas_list));
                genericList.add(new
                        MathematicsWolf("Diferenciales", R.drawable.diferenciacion));
                genericList.add(new
                        MathematicsWolf("Limites", R.drawable.limite));
                break;
            case Global.ecuaciones:
                menu = Global.ecuacioness;
                genericList.add(new MathematicsWolf("Constantes", R.drawable.constantes));
                genericList.add(new MathematicsWolf("Coseno", R.drawable.cosec));
                genericList.add(new MathematicsWolf("Cubica", R.drawable.cubica));
                genericList.add(new MathematicsWolf("Cuadratica", R.drawable.ecuacion_cuadratica));
                genericList.add(new MathematicsWolf("Exponencial", R.drawable.ecuacion_exponencial));


                break;
            case Global.estadistica:
                menu = Global.estadisticas;
                genericList.add(new MathematicsWolf("combinatoria", R.drawable.combinatoria));
                genericList.add(new MathematicsWolf("Complejo Relativo", R.drawable.complemento_relativode_a_en_b));
                genericList.add(new MathematicsWolf("Complejo Absoluto", R.drawable.complementoabsoluto));
                genericList.add(new MathematicsWolf("Conjunto", R.drawable.conjunto));
                genericList.add(new MathematicsWolf("Desviacion tipica", R.drawable.desviaciontipica));

                break;
            case Global.integrales:
                menu = Global.integraless;
                genericList.add(new MathematicsWolf("Aplicaciones", R.drawable.aplicaciones));
                genericList.add(new MathematicsWolf("Integrales por fracciones parciales", R.drawable.integralesporfraccionesparciales));
                genericList.add(new MathematicsWolf("Integrales que involucran funciones trigonometricas", R.drawable.integralesqueinvolucranfuncionestrigonometricas));
                genericList.add(new MathematicsWolf("Integrales que involucran raices", R.drawable.integralesqueinvolucranraices));


                break;
        }


        return genericList;
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
