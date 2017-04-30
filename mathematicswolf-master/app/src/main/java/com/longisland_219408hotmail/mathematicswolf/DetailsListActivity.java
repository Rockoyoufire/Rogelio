package com.longisland_219408hotmail.mathematicswolf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.longisland_219408hotmail.mathematicswolf.Bean.Global;
import com.longisland_219408hotmail.mathematicswolf.adapter.ListAdapter;
import com.longisland_219408hotmail.mathematicswolf.adapter.RvImageAdapter;
import com.longisland_219408hotmail.mathematicswolf.model.MathematicsWolf;

import java.util.ArrayList;
import java.util.List;

public class DetailsListActivity extends AppCompatActivity {

    private int typeList;
    private String menu;
    private List<MathematicsWolf> mathematicsWolfList = new ArrayList<>();

    private RvImageAdapter rv_details;
    RecyclerView rvImageTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvImageTop = (RecyclerView) findViewById(R.id.rv_details);
        if (getIntent().getExtras() != null) {
            typeList = getIntent().getExtras().getInt("type");
            menu = getIntent().getExtras().getString("menu");
            mathematicsWolfList = getList(typeList, menu);
            rv_details = new RvImageAdapter(DetailsListActivity.this, mathematicsWolfList);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            rvImageTop.setLayoutManager(mLayoutManager);
            rvImageTop.setItemAnimator(new DefaultItemAnimator());
            rvImageTop.setAdapter(rv_details);

        }


    }

    List<MathematicsWolf> getList(int type, String menu) {
        List<MathematicsWolf> genericList = new ArrayList<>();

        if (menu.equals(Global.derivadass)) {
            switch (type) {
                case 0:
                    genericList.add(new
                            MathematicsWolf("", R.drawable.derivadas1));
                    genericList.add(new
                            MathematicsWolf("", R.drawable.derivadas2));
                    genericList.add(new
                            MathematicsWolf("", R.drawable.derivadas3));
                    break;

                case 1:
                    genericList.add(new
                            MathematicsWolf("", R.drawable.diferenciacion1));
                    break;
                case 2:

                    genericList.add(new
                            MathematicsWolf("", R.drawable.limite1));
                    genericList.add(new
                            MathematicsWolf("", R.drawable.limite2));
                    genericList.add(new
                            MathematicsWolf("", R.drawable.limite3));
                    break;

            }
        }

        if (menu.equals(Global.ecuacioness)) {
            switch (type) {
                case 0:
                    genericList.add(new MathematicsWolf("", R.drawable.constante1));

                    break;

                case 1:
                    genericList.add(new MathematicsWolf("", R.drawable.cosec1));
                    break;
                case 2:
                    genericList.add(new MathematicsWolf("", R.drawable.cubica1));

                    break;
                case 3:
                    genericList.add(new MathematicsWolf("", R.drawable.ecuacioncuadratica1));
                    genericList.add(new MathematicsWolf("", R.drawable.ecuacioncuadratica2));
                    break;
                case 4:
                    genericList.add(new MathematicsWolf("", R.drawable.ecuacionexpotencial1));

                    break;

            }
        }

        if (menu.equals(Global.estadisticas)) {
            switch (type) {
                case 0:
                    genericList.add(new MathematicsWolf("", R.drawable.combinatoria1));

                    break;

                case 1:
                    genericList.add(new MathematicsWolf("", R.drawable.completamenterelativoaenb));
                    break;
                case 2:
                    genericList.add(new MathematicsWolf("", R.drawable.complementoabsoluto1));

                    break;
                case 3:
                    genericList.add(new MathematicsWolf("", R.drawable.conjunto1));

                    break;
                case 4:
                    genericList.add(new MathematicsWolf("", R.drawable.desviaciontipica1));

                    break;

            }
        }

        if (menu.equals(Global.integraless)) {
            switch (type) {
                case 0:
                    genericList.add(new MathematicsWolf("", R.drawable.aplicaciones1));
                    genericList.add(new MathematicsWolf("", R.drawable.aplicaciones2));

                    break;

                case 1:
                    genericList.add(new MathematicsWolf("", R.drawable.integralesporfraccionesparciales1));
                    genericList.add(new MathematicsWolf("", R.drawable.integralesporfraccionesparciales2));
                    break;
                case 2:
                    genericList.add(new MathematicsWolf("", R.drawable.integralesqueinvolucranfuncionestrigonometricas1));
                    genericList.add(new MathematicsWolf("", R.drawable.integralesqueinvolucranfuncionestrigonometricas2));

                    break;
                case 3:
                    genericList.add(new MathematicsWolf("", R.drawable.integralesqueinvolucranraices1));

                    break;


            }
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
