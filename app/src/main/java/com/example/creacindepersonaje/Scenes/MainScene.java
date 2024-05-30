package com.example.creacindepersonaje.Scenes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.creacindepersonaje.Campaign.AddCampaign;
import com.example.creacindepersonaje.R;

public class MainScene extends AppCompatActivity {

    private Button bt_addScene;
    private Button bt_listScenes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scene);
        IniciarComponentes();
        IniciarEventos();
    }

    private void IniciarComponentes(){
        bt_addScene = findViewById(R.id.bt_addScene);
        bt_listScenes = findViewById(R.id.bt_listScenes);
    }

    private void IniciarEventos(){
        bt_addScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), CrearScenes.class);
                startActivity(i);

            }
        });

        bt_listScenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ListarScenes.class);
                startActivity(i);

            }
        });
    }
}