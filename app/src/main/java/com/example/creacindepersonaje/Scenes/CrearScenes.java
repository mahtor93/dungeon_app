package com.example.creacindepersonaje.Scenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.CampaignController;
import com.example.creacindepersonaje.Controller.SceneController;
import com.example.creacindepersonaje.Monstruo.CreateAMonster;
import com.example.creacindepersonaje.Guild.CreateAGuild;
import com.example.creacindepersonaje.Guild.Guild;
import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.Modelos.Escenas;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class CrearScenes extends AppCompatActivity {

    private EditText tv_nameScene;
    private ArrayList<Escenas> escenas;
    private ArrayList<String> nombreEscena;
    private ArrayList<Campaing> campaings;
    private ArrayList<String> nCampaigns;
    private ArrayAdapter<String> adaScene;
    private ArrayAdapter<String> adaCampaign;
    private Button bt_crearScene;
    private Button bt_volverScenes;
    private Spinner sp_campaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_scenes);
        IniciarComponentes();
        IniciarEventos();
    }

    private void iniciarActividad(Class clase){
        Intent intent = new Intent(this,clase);
        startActivity(intent);
    }

    private void IniciarComponentes(){
        tv_nameScene = findViewById(R.id.tv_nameScene);
        bt_crearScene = findViewById(R.id.bt_crearScene);
        bt_volverScenes = findViewById(R.id.bt_volverScenes);
        sp_campaign = findViewById(R.id.sp_campaign);
    }

    private void IniciarEventos(){

        escenas = new ArrayList<>();
        nombreEscena = new ArrayList<>();
        campaings = new ArrayList<>();
        nCampaigns = new ArrayList<>();

        try {
            campaings = CampaignController.listarCampañas(getApplicationContext());
            for (Campaing item: campaings) {
                nCampaigns.add(item.getNombreCampaing());
            }
        } catch (Exception ex){
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }
        adaCampaign = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, nCampaigns);
        adaCampaign.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_campaign.setAdapter(adaCampaign);

        bt_crearScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tv_nameScene.getText().toString().length() == 0) {
                    Toast.makeText(CrearScenes.this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (sp_campaign.getSelectedItem().toString() == ""){
                        Toast.makeText(CrearScenes.this, "Debe elegir una campaña asignada", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            String result = sp_campaign.getSelectedItem().toString();
                            int idCampaign = CampaignController.findCampByName(getApplicationContext(),result);
                            Escenas e = new Escenas();
                            e.setNombreEscena(tv_nameScene.getText().toString());
                            e.setCampaignAsignada(idCampaign);
                            SceneController.createScene(getApplicationContext(), e);
                        } catch (Exception ex){
                            Toast.makeText(CrearScenes.this, "Error al crear :"+ex, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        bt_volverScenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(MainScene.class);
            }
        });





    }
}