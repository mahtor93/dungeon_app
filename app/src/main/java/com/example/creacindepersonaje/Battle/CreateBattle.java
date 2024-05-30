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

public class CreateBattle extends AppCompatActivity {

    private EditText etNameBattle, etRelato;
    private Button btnCreateBattle,btnCancelBattleCreation;
    private ListView lvMonstruos;

    private ArrayList<Monstruo> monsters;
    private ArrayList<String> nameMonsters;
    private ArrayAdapter<String> adapterMonsters;
    private Battle bat = new Battle();
    private int code = 1;
    private String error = "";
    private String name;
    private String description;
    private int idMonstruo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_battle);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        etNameBattle = findViewById(R.id.etNameBattle);
        etRelato =findViewById(R.id.etRelato);
        btnCreateBattle=findViewById(R.id.btnCreateBattle);
        btnCancelBattleCreation=findViewById(R.id.btnCancelBattleCreation);
        lvMonstruos=findViewById(R.id.lvMonstruos);
    }

    private void iniciarActividad(Class clase){
        Intent intent = new Intent(getApplicationContext(),clase);
        startActivity(intent);
    }

    private void iniciarEventos(){
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
        lvMonstruos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvMonstruos.setAdapter(adapterMonsters);



        lvMonstruos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(CreateBattle.this, "item: "+nameMonsters.get(i), Toast.LENGTH_SHORT).show();
                try {
                    idMonstruo = MonsterController.getIdPorNombre(getApplicationContext(), nameMonsters.get(i));
                }catch (Exception ex){
                    Toast.makeText(CreateBattle.this, "Error getIdPorNombre "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });




        btnCancelBattleCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(BattleMain.class);
            }
        });

        btnCreateBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                error = "";
                code=1;
                name = etNameBattle.getText().toString();
                description = etRelato.getText().toString();

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
                        bat.setIdGuild(-1);
                        BattleController.createBattle(getApplicationContext(), bat);
                        iniciarActividad(BattleMain.class);
                    } catch (Exception ex) {
                        Toast.makeText(CreateBattle.this, "Error createBattle " + ex, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(CreateBattle.this, "Error\n" + error, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}