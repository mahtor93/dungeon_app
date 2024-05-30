package com.example.creacindepersonaje.Guild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.GuildController;
import com.example.creacindepersonaje.Modelos.modelGuild;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class VistaAllGuilds extends AppCompatActivity {

    private ListView guildsList;
    private ArrayList<modelGuild> guilds;
    private ArrayList<String> guildNames;
    private ArrayAdapter<String> adaGuild;

    private void iniciarActividad(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_all_guilds);
        iniciarComponentes();
        iniciarEventos();
        adaGuild.notifyDataSetChanged();
    }

    private void iniciarComponentes(){
        guildsList = findViewById(R.id.guildsList);
    }

    private modelGuild buildGuild(String gremio){
        modelGuild mg = new modelGuild();
        mg.setGuildName(gremio);
        for(int i =0;i<guilds.size();i++){ //Iteramos sobre la lista de objetos guilds para obtener la ID
            if(gremio == guilds.get(i).getGuildName()){
                mg.setIdGuild(guilds.get(i).getIdGuild());
            }
        }
        try {
            //Debemos crear una lista con los integrantes de la guild.
            mg.setIntegrantes(GuildController.listarIntegrantes(getApplicationContext(), mg.getIdGuild()));
            return mg;
        }catch (Exception ex){
            Toast.makeText(VistaAllGuilds.this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }
        finally {
            return mg;
        }
    }
    private void iniciarEventos(){
        guilds = new ArrayList<>();
        guildNames = new ArrayList<>();

        try {

            guilds = GuildController.listarGuilds(getApplicationContext()); //Obtenemos la lista de guilds
            for(modelGuild guild: guilds){
                guildNames.add(guild.getGuildName()); //Obtenemos el parámetro nombre para mostrarlo en la listview
            }
            adaGuild = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, guildNames);
            guildsList.setAdapter(adaGuild);

        } catch (Exception ex){
            Toast.makeText(this, "Error : "+ex, Toast.LENGTH_SHORT).show();
        }

        guildsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        guildsList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int itemSeleccionadoEnPosicion;

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                itemSeleccionadoEnPosicion = i;
                actionMode.setTitle("Opciones");
                actionMode.setSubtitle("Para " + guilds.get(i).getGuildName().toString());

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.menu_campaign, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                String gremio = guildNames.get(itemSeleccionadoEnPosicion);
                int id = menuItem.getItemId();
                modelGuild mg = buildGuild(gremio);

                switch (id){
                    case R.id.opcion_Editar:
                        Toast.makeText(VistaAllGuilds.this, ""+gremio, Toast.LENGTH_SHORT).show();
                        iniciarActividad(VistaAllGuilds.class);
                        break;
                    case R.id.opcion_Eliminar:
                        try {
                            GuildController.removeGuild(getApplicationContext(),mg);
                            iniciarActividad(VistaAllGuilds.class);
                        }catch (Exception ex){
                            Toast.makeText(VistaAllGuilds.this, "error "+ex, Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "eliminado con Exito " +gremio, Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        guildsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name=guildNames.get(i);

                try {
                    int iDGuild = GuildController.findGuildByName(getApplicationContext(), name);
                    Intent intent = new Intent(getApplicationContext(),ViewGuild.class);
                    intent.putExtra("IDGUILD",iDGuild);
                    startActivity(intent);

                }catch (Exception ex){
                    Toast.makeText(VistaAllGuilds.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}