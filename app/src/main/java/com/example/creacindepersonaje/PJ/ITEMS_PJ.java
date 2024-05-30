package com.example.creacindepersonaje.PJ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Armadura;
import com.example.creacindepersonaje.R;
import com.example.creacindepersonaje.Jugador.verItemSeleccionado;

import java.util.ArrayList;

public class ITEMS_PJ extends AppCompatActivity {

    private TextView tvItems;
    private ListView lv_Armaduras;
    private ListView lv_Armas;

    private ArrayList<String> items;
    private ArrayList<String> nameArmaduras;

    private ArrayList<Integer> idArmas;
    private ArrayList<Integer> idArmaduras;

    private ArrayAdapter<String> adap_Armas;
    private ArrayAdapter<String> adap_Armaduras;


    int id_pj;
    int idArma;
    int idArmadura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_items_pj);
            iniciarComponentes();
            iniciarEventos();

    }
    private void iniciarComponentes(){
        tvItems = findViewById(R.id.tvItems);
        lv_Armaduras = findViewById(R.id.lv_Armaduras);
        lv_Armas = findViewById(R.id.lv_Armas);
    }
    private void iniciarEventos(){
        try {
            //String name;
            nameArmaduras = new ArrayList<>();
            items = new ArrayList<>();

            idArmaduras = new ArrayList<>();
            idArmas = new ArrayList<>();

            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();

            String name = extra.getString("NAME");//Nombre del personaje
            idArma = extra.getInt("ID_ARMA"); //ID ARMA
            idArmadura = extra.getInt("ID_ARMADURA"); //ID ARMADURA
            id_pj = extra.getInt("ID_PJ");

            idArmas.add(idArma);
            idArmaduras.add(idArmadura);

            tvItems.setText("Inventario de " + name);

            //items = ItemsController.getItemsPJ(getApplicationContext(), idArma, idArmadura);
            Arma a = ItemsController.findArma(getApplicationContext(),idArma);
            items.add(a.getNombre());

            Armadura ar = ItemsController.findArmadura(getApplicationContext(),idArmadura);
            nameArmaduras.add(ar.getNombre());


            adap_Armaduras = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,nameArmaduras);
            lv_Armaduras.setAdapter(adap_Armaduras);

            adap_Armas = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,items);
            lv_Armas.setAdapter(adap_Armas);

        }catch(Exception ex){
            Toast.makeText(this, "error 1 "+ex, Toast.LENGTH_SHORT).show();
        }

        lv_Armas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {

                    int idArma = idArmas.get(i);
                    Intent intent = new Intent(getApplicationContext(), verItemSeleccionado.class);
                    intent.putExtra("ID_ARMA", idArma);
                    intent.putExtra("ID_PJ", id_pj);
                    intent.putExtra("unotro", 0);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(ITEMS_PJ.this, "Error 2 "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });



        lv_Armaduras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    int idArmadura = idArmaduras.get(i);
                    Intent intent = new Intent(getApplicationContext(),verItemSeleccionado.class);
                    intent.putExtra("idArmadura",idArmadura);
                    intent.putExtra("ID_PJ",id_pj);
                    intent.putExtra("unotro",1);
                    startActivity(intent);

                }catch(Exception ex){
                    Toast.makeText(ITEMS_PJ.this, "error 3 "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}