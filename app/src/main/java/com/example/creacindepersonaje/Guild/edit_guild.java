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
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class edit_guild extends AppCompatActivity {

    private EditText etRenameGuild;
    private ListView lvPErsonajesSinGuild;
    private Button btEditGuild, btCancelar;
    private ArrayList<Personaje> personajes;
    private ArrayList<Personaje> selectedPersonajes;
    private ArrayList<String> personajesSinGuild;
    private ArrayAdapter<String> adapterSinGuild;
    private ArrayList<String> selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guild);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes(){
        etRenameGuild = findViewById(R.id.etRenameGuild);
        lvPErsonajesSinGuild = findViewById(R.id.lvPersonajesSinGuild);
        btEditGuild = findViewById(R.id.btEditGuild);
        btCancelar = findViewById(R.id.btCancelar);
    }
    private void iniciarEventos(){
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        int ID = extra.getInt("ID_GUILD");
        personajes = new ArrayList<>();
        personajesSinGuild = new ArrayList<>();
        selected = new ArrayList<>();
        selectedPersonajes = new ArrayList<>();
        try{
            personajes = PersonajeController.listPersonajes(getApplicationContext());
            for(Personaje item : personajes){
                if(item.getIdGuild()==-1||item.getIdGuild()==0){
                    personajesSinGuild.add(item.getNombre());
                }
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error al conseguir lista de personajes: "+ex, Toast.LENGTH_SHORT).show();
        }
        adapterSinGuild = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,personajesSinGuild);
        lvPErsonajesSinGuild.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvPErsonajesSinGuild.setAdapter(adapterSinGuild);
        adapterSinGuild.notifyDataSetChanged();

        lvPErsonajesSinGuild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (lvPErsonajesSinGuild.isItemChecked(i)) {
                        selected.add(lvPErsonajesSinGuild.getItemAtPosition(i).toString());
                    }
                    if (!lvPErsonajesSinGuild.isItemChecked(i)) {
                        selected.remove(i);
                    }
                }catch (Exception ex){
                    Toast.makeText(edit_guild.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btEditGuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String name = etRenameGuild.getText().toString();
                    int id = GuildController.findGuildByName(getApplicationContext(),name);
                    if(id==-1) {

                        for (int i = 0; i < selected.size(); i++) {
                            Personaje p = new Personaje();
                            p.setIdPersonaje(PersonajeController.getIdPorNombre(getApplicationContext(), selected.get(i)));
                            selectedPersonajes.add(p);
                        }
                        if (etRenameGuild.getText().length() != 0) {
                            GuildController.renameGuild(getApplicationContext(), ID, name);
                            Toast.makeText(edit_guild.this, "Renombrado con exito " + etRenameGuild.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                        if (selectedPersonajes.size() != 0) {
                            for (int i = 0; i < selectedPersonajes.size(); i++) {
                                GuildController.addPersonaje(getApplicationContext(), selectedPersonajes.get(i).getIdPersonaje(), ID);
                                Toast.makeText(edit_guild.this, "Añadidos con exito ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Toast.makeText(edit_guild.this, "El nombre ya está en uso", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(edit_guild.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}