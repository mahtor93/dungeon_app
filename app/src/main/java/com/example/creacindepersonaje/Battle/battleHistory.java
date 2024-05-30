package com.example.creacindepersonaje.Battle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.BattleController;
import com.example.creacindepersonaje.Modelos.Battle;
import com.example.creacindepersonaje.Modelos.LogBattle;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class battleHistory extends AppCompatActivity {

    private TextView tvBattleHistory;

    private ListView lvHistorias;

    private int idBattle;

    private ArrayList<LogBattle> logs;
    private ArrayList<String> histories;
    private ArrayAdapter<String> adapterHistories;

    private Battle b = new Battle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_history);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        tvBattleHistory = findViewById(R.id.tvBattleHistory);
        lvHistorias = findViewById(R.id.lvHistorias);
    }

    private void iniciarEventos(){
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        idBattle = extra.getInt("IDBATTLE");
        histories = new ArrayList<>();


        try{
            b = BattleController.getBattleById(getApplicationContext(),idBattle);
            tvBattleHistory.setText(b.getNameBattle()+"");
            logs = BattleController.listLog(getApplicationContext(),idBattle);
            for(LogBattle logs : logs){
                histories.add(logs.getDescripcion());
            }
        }catch (Exception ex){
            Toast.makeText(this, "error al obtener registros "+ex, Toast.LENGTH_SHORT).show();
        }
        adapterHistories = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,histories);
        lvHistorias.setAdapter(adapterHistories);

    }
}