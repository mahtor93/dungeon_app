package com.example.creacindepersonaje.Jugador;

import static android.media.CamcorderProfile.get;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.ItemsController;
import com.example.creacindepersonaje.Modelos.Arma;
import com.example.creacindepersonaje.Modelos.Armadura;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class Forja extends AppCompatActivity {


    private Button btnForjarArma, btnForjarArmadura;
    private ListView lvArmas;
    private ArrayList<Arma> armas = new ArrayList<>();

    private ArrayAdapter<String> adapterArmas;
    private ArrayList<String> nameArmas;
    private ArrayList<Integer> idArmas;

    private ListView lvArmaduras;
    private ArrayList<Armadura> armaduras = new ArrayList<>();
    private ArrayAdapter<String> adapterArmaduras;
    private ArrayList<String> nameArmaduras;
    private ArrayList<Integer> idArmaduras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forja);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        lvArmas = findViewById(R.id.lvArmas);
        lvArmaduras = findViewById(R.id.lvArmaduras);
        btnForjarArma =findViewById(R.id.bnForjarArma);
        btnForjarArmadura=findViewById(R.id.btnForjarArmadura);
    }
    private void iniciarEventos(){

        nameArmas = new ArrayList<>();
        idArmas = new ArrayList<>();

        //armaduras = new ArrayList<>();
        nameArmaduras = new ArrayList<>();
        idArmaduras = new ArrayList<>();

        try{
            armas = ItemsController.getArmas(getApplicationContext());
            armaduras = ItemsController.getArmaduras(getApplicationContext());

            for(int ar = 0; ar<armas.size();ar++){
                nameArmas.add(armas.get(ar).getNombre());
                idArmas.add(armas.get(ar).getIdArma());
            }
            for(int amd =0; amd<armaduras.size();amd++){
                nameArmaduras.add(armaduras.get(amd).getNombre());
                idArmaduras.add(armaduras.get(amd).getIdArmadura());
            }

            adapterArmas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nameArmas);
            lvArmas.setAdapter(adapterArmas);
            adapterArmaduras = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameArmaduras);
            lvArmaduras.setAdapter(adapterArmaduras);

        }catch(Exception ex){
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }

        lvArmas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    int id_arma = armas.get(i).getIdArma();
                    Intent intent = new Intent(getApplicationContext(),verItemSeleccionado.class);
                    intent.putExtra("ID_ARMA",id_arma);
                    intent.putExtra("unotro",0);
                    startActivity(intent);
                }catch(Exception ex){
                    Toast.makeText(Forja.this, "error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnForjarArma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),create_Arma.class);
                startActivity(i);
            }
        });

        btnForjarArmadura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),create_armadura.class);
                startActivity(i);
            }
        });

        lvArmaduras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    int id_armadura = armaduras.get(i).getIdArmadura();
                    Intent intent = new Intent(getApplicationContext(),verItemSeleccionado.class);
                    intent.putExtra("idArmadura",id_armadura);
                    intent.putExtra("unotro",1);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(Forja.this, "error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}