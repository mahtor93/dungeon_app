package com.example.creacindepersonaje.Jugador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.creacindepersonaje.Guild.Guild;
import com.example.creacindepersonaje.PJ.List_Personajes;
import com.example.creacindepersonaje.PJ.MainActivity;
import com.example.creacindepersonaje.R;

public class Jugador extends AppCompatActivity {


    private Button btnPersonajes;
    private Button btnCrear;
    private Button btnGuild;
    private Button btnForja;
    private Button btnSpells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        btnPersonajes = findViewById(R.id.btnPersonajes);
        btnCrear = findViewById(R.id.btnCrear);
        btnGuild = findViewById(R.id.btnGuild);
        btnForja = findViewById(R.id.btnForja);
        btnSpells = findViewById(R.id.btnSpells);
    }

    private void iniciarActividad(Class clase){
        Intent intent = new Intent(this,clase);
        startActivity(intent);
    }

    private void iniciarEventos(){
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(MainActivity.class);
            }
        });

        btnPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(List_Personajes.class);
            }
        });

        btnForja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(Forja.class);
            }
        });
        btnSpells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(Spellbook.class);
            }
        });

        btnGuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { iniciarActividad(Guild.class);

            }
        });
    }
}