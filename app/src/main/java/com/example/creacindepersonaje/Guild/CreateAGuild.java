package com.example.creacindepersonaje.Guild;

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

import com.example.creacindepersonaje.Controller.GuildController;
import com.example.creacindepersonaje.Controller.PersonajeController;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.Modelos.modelGuild;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class CreateAGuild extends AppCompatActivity {

    private EditText et_nameGuild;
    private Button bt_createGuild;
    private Button bt_back;
    long w[];

    private ListView lvPersonajesGuild;
    private ArrayList<Personaje> personajes;
    private ArrayList<Personaje> selectedPersonajesId;
    //private ArrayList<Integer> unselectedPersonajes;
    private ArrayList<String> personajesGuild;
    private ArrayAdapter<String> adapterGuilds;


    private ArrayList<String> selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_aguild);
        IniciarComponentes();
        IniciarEventos();

    }

    private void IniciarComponentes(){
        et_nameGuild = findViewById(R.id.et_nameGuild);
        bt_createGuild = findViewById(R.id.bt_createGuild);
        bt_back = findViewById(R.id.bt_back);
        lvPersonajesGuild = findViewById(R.id.lvPersonajesGuild);

    }

    private void iniciarActividad(Class clase){
        Intent intent = new Intent(this,clase);
        startActivity(intent);
    }

    private void IniciarEventos(){
        personajes = new ArrayList<>();
        personajesGuild = new ArrayList<>();
        selected = new ArrayList<>();
        selectedPersonajesId = new ArrayList<>();
        try {
            personajes = PersonajeController.listPersonajes(getApplicationContext()); //Se listan todos los personajes

            for(Personaje item : personajes){
                if(item.getIdGuild()==-1){
                    personajesGuild.add(item.getNombre());//+"\n"+item.getRaza()+" "+item.getSub_Raza()+" "+item.getClase()+" "+item.getLevel());
                }
            }

            adapterGuilds = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,personajesGuild);
            lvPersonajesGuild.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            lvPersonajesGuild.setAdapter(adapterGuilds);

        }catch (Exception ex){
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }
        lvPersonajesGuild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //SparseBooleanArray sp = lvPersonajesGuild.getCheckedItemPositions();

                    if(lvPersonajesGuild.isItemChecked(i)){
                        //Si el item es chequeado, retorna true y se puede operar sobre el item seleccionado.
                        selected.add(lvPersonajesGuild.getItemAtPosition(i).toString());
                    }
                    if(!lvPersonajesGuild.isItemChecked(i)){
                        selected.remove(i);
                    }
            }
        });
        bt_createGuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = GuildController.findGuildByName(getApplicationContext(),et_nameGuild.getText().toString());
                    if(id==-1){
                        for (int i = 0; i < selected.size(); i++) {
                            Personaje p = new Personaje();
                            p.setIdPersonaje(PersonajeController.getIdPorNombre(getApplicationContext(), selected.get(i)));
                            selectedPersonajesId.add(p);
                        }
                        modelGuild g = new modelGuild();
                        g.setGuildName(et_nameGuild.getText().toString());
                        g.setIntegrantes(selectedPersonajesId);
                        GuildController.createGuild(getApplicationContext(), g);
                        Intent i = new Intent(getApplicationContext(),VistaAllGuilds.class);
                        startActivity(i);
                        }else{
                        Toast.makeText(CreateAGuild.this, "El nombre ya está en uso", Toast.LENGTH_SHORT).show();
                        }
                }catch (Exception ex){
                    Toast.makeText(CreateAGuild.this, "Error al crear :"+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }}