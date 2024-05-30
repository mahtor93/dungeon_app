package com.example.creacindepersonaje.Guild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.creacindepersonaje.R;

public class Guild extends AppCompatActivity {
    private Button bt_crearGuild;
    private Button bt_listarGuilds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarActividad(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    private void iniciarComponentes() {
        bt_crearGuild = findViewById(R.id.bt_crearGuild);

        bt_listarGuilds = findViewById(R.id.bt_listarGuild);

    }
    private void iniciarEventos() {

        bt_crearGuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(CreateAGuild.class);
            }
        });


        bt_listarGuilds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(VistaAllGuilds.class);
            }
        });
    }
}