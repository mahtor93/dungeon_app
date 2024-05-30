package com.example.creacindepersonaje.Battle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.BattleController;
import com.example.creacindepersonaje.Controller.MonsterController;
import com.example.creacindepersonaje.Modelos.Battle;
import com.example.creacindepersonaje.Modelos.Monstruo;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class battleEditor extends AppCompatActivity {

    private EditText etNameBattleEdit, etRelatoEdit;
    private Button btnEditBattle,btnCancelBattleEdit;
    private ListView lvMonstruosEdit;

    private ArrayList<Monstruo> monsters;
    private ArrayList<String> nameMonsters;
    private ArrayAdapter<String> adapterMonsters;
    private Battle bat = new Battle();
    private int code = 1;
    private String error = "";
    private String name;
    private String description;
    private int idMonstruo = -1;
    private int idBattle;

    private Battle batalla = new Battle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_editor);
        iniciarComponentes();
        iniciarEventos();

    }

    private void iniciarComponentes(){
            etNameBattleEdit = findViewById(R.id.etNameBattleEdit);
            etRelatoEdit = findViewById(R.id.etRelatoEdit);
            btnEditBattle = findViewById(R.id.btnEditBattle);
            btnCancelBattleEdit = findViewById(R.id.btnCancelBattleEdit);
            lvMonstruosEdit = findViewById(R.id.lvMonstruosEdit);

    }

    private void iniciarActividad(Class clase){
        Intent intent = new Intent(getApplicationContext(),clase);
        startActivity(intent);
    }

    private void iniciarEventos(){

        try {
            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            idBattle = extra.getInt("IDBATTLE");

        }catch (Exception ex){
            Toast.makeText(this, "Error intent Bundle Extra "+ex, Toast.LENGTH_SHORT).show();
        }
        try {
            batalla = BattleController.getBattleById(getApplicationContext(), idBattle);
            etNameBattleEdit.setText(batalla.getNameBattle());
            etRelatoEdit.setText(batalla.getDescripcion());
        }catch (Exception ex){
            Toast.makeText(this, "Error getBattleById "+ex, Toast.LENGTH_SHORT).show();
        }
        monsters = new ArrayList<>();
        nameMonsters = new ArrayList<>();



        try {
            monsters = MonsterController.listMonsters(getApplicationContext());
            for(Monstruo m : monsters){
                nameMonsters.add(m.getNameMonstruo());
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error listMonsters "+ex, Toast.LENGTH_SHORT).show();
        }

        adapterMonsters = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked,nameMonsters);
        lvMonstruosEdit.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvMonstruosEdit.setAdapter(adapterMonsters);



        lvMonstruosEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(battleEditor.this, "item: "+nameMonsters.get(i), Toast.LENGTH_SHORT).show();
                try {
                    idMonstruo = MonsterController.getIdPorNombre(getApplicationContext(), nameMonsters.get(i));
                }catch (Exception ex){
                    Toast.makeText(battleEditor.this, "Error getIdPorNombre "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelBattleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(BattleMain.class);
            }
        });

        btnEditBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error = "";
                name = etNameBattleEdit.getText().toString();
                description = etRelatoEdit.getText().toString();
                if(name.length()==0){
                    error += "Ingresa un nombre\n";
                    code++;
                }else{
                    bat.setNameBattle(name);
                }
                if(description.length()==0){
                    error +="Ingresa una descripción\n";
                    code++;
                }else{
                    bat.setDescripcion(description);
                }
                if(idMonstruo==-1){
                    error+="Selecciona un monstruo\n";
                    code++;
                }else{
                    bat.setIdMonstruo(idMonstruo);
                }
                if (error.length()==0) {
                    try {
                        bat.setIdBattle(batalla.getIdBattle());
                        BattleController.editBattle(getApplicationContext(),bat);
                        iniciarActividad(BattleMain.class);
                    } catch (Exception ex) {
                        Toast.makeText(battleEditor.this, "Error editBattle " + ex, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(battleEditor.this, "Error\n" + error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}