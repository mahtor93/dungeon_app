package com.example.creacindepersonaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.creacindepersonaje.Battle.BattleMain;
import com.example.creacindepersonaje.Campaign.MainCampaign;
import com.example.creacindepersonaje.Monstruo.MainMonstruos;

public class DungeonMaster extends AppCompatActivity {

    private Button bt_adminMonst;
    private Button bt_adminBattle;
    private Button bt_adminCampaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon_master);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarActividad(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }
    private void iniciarComponentes() {
        bt_adminMonst = findViewById(R.id.bt_adminMonst);
        bt_adminBattle = findViewById(R.id.bt_adminBattle);
        bt_adminCampaign = findViewById(R.id.bt_adminCampaign);
    }
    private void iniciarEventos() {

        bt_adminMonst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(MainMonstruos.class);
            }
        });
        bt_adminBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(BattleMain.class);
            }
        });

        bt_adminCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { iniciarActividad(MainCampaign.class);

            }
        });

    }


}