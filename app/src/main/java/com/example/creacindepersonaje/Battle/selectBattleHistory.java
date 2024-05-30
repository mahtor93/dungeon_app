package com.example.creacindepersonaje.Battle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.BattleController;
import com.example.creacindepersonaje.Modelos.Battle;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class selectBattleHistory extends AppCompatActivity {

    private ListView lvBattlesHistory;

    private ArrayList<Battle> getBattles;
    private ArrayList<String> listBattles;
    private ArrayAdapter<String> adapterBattles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_battle_history);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        lvBattlesHistory = findViewById(R.id.lvBattlesHistory);
    }
    private void iniciarEventos(){
        listBattles = new ArrayList<>();
        try{
            getBattles = BattleController.listarBatallas(getApplicationContext());
            for(Battle bat : getBattles){
                listBattles.add(bat.getNameBattle());
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error listarBatallas "+ex, Toast.LENGTH_SHORT).show();
        }
        adapterBattles = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listBattles);
        lvBattlesHistory.setAdapter(adapterBattles);

        lvBattlesHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    int idBattle = BattleController.getIdByName(getApplicationContext(),lvBattlesHistory.getItemAtPosition(i).toString());
                    Intent intent = new Intent(getApplicationContext(),battleHistory.class);
                    intent.putExtra("IDBATTLE",idBattle);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(selectBattleHistory.this, "Error getIdByname "+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}