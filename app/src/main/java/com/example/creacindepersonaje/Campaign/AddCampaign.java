package com.example.creacindepersonaje.Campaign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.CampaignController;
import com.example.creacindepersonaje.Guild.Guild;
import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class AddCampaign extends AppCompatActivity {

    private EditText tv_nameGuild,tvRelatoCampaign;
    private ArrayList<String> nGuilds;
    private ArrayList<Guild> tipoGuild;
    private Button bt_editarScene;
    private Button bt_volverCampaign;
    private ArrayAdapter<String> adaGuilds;
    Campaing c = new Campaing();
    private boolean name = true;
    private boolean relato = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_campaign);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        tv_nameGuild = findViewById(R.id.tv_nameScene);
        bt_editarScene = findViewById(R.id.bt_crearScene);
        bt_volverCampaign = findViewById(R.id.bt_volverScenes);
        tvRelatoCampaign = findViewById(R.id.tvRelatoCampaign);
    }

    private void iniciarEventos(){
        bt_editarScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_nameGuild.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Ingrese nombre de la campaña", Toast.LENGTH_SHORT).show();
                    name=false;
                }
                if(tvRelatoCampaign.getText().toString().length()==0){
                    Toast.makeText(AddCampaign.this, "Ingresa un relato", Toast.LENGTH_SHORT).show();
                    relato=false;
                }
                try{
                if(CampaignController.findCampByName(getApplicationContext(),tv_nameGuild.getText().toString())!=-1){
                    Toast.makeText(AddCampaign.this, "El nombre ya está en uso", Toast.LENGTH_SHORT).show();
                    name=false;
                }
                    if(name&&relato){
                        String nombreCamp = tv_nameGuild.getText().toString();
                        try {
                            c.setNombreCampaing(nombreCamp);
                            c.setRelatoCampaign(tvRelatoCampaign.getText().toString()); // hasta acá podríamos agregar más variables, pero habria que crear en la db
                            c.setIdCampaign(-1);
                            CampaignController.createCampaign(getApplicationContext(), c);
                            Toast.makeText(getApplicationContext(), "Creado con éxito", Toast.LENGTH_SHORT).show();
                            Intent i =  new Intent(getApplicationContext(), ListarCampaign.class);
                            startActivity(i);
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Error" + ex, Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex){
                    Toast.makeText(AddCampaign.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_volverCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainCampaign.class);
                startActivity(i);
            }
        });



    }
}