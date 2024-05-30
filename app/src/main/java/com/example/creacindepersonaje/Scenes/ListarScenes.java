package com.example.creacindepersonaje.Scenes;

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

import com.example.creacindepersonaje.Controller.CampaignController;
import com.example.creacindepersonaje.Controller.GuildController;
import com.example.creacindepersonaje.Controller.SceneController;
import com.example.creacindepersonaje.Guild.ViewGuild;
import com.example.creacindepersonaje.Guild.VistaAllGuilds;
import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.Modelos.Escenas;
import com.example.creacindepersonaje.Modelos.modelGuild;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class ListarScenes extends AppCompatActivity {

    private ListView ListScenesCampaign;
    private ArrayList<Escenas> escenas;
    private ArrayList <String> nEscenas;
    private ArrayAdapter<String> adaScene;

    private void iniciarActividad(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    private Escenas buildScene(String gremio){
        Escenas mg = new Escenas();
        mg.setNombreEscena(gremio);
        for(int i =0;i<escenas.size();i++){
            if(gremio == escenas.get(i).getNombreEscena()){
                mg.setIdEscena(escenas.get(i).getIdEscena());
            }
        }
        return mg;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_scenes);

        IniciarComponentes();
        IniciarEventos();
        adaScene.notifyDataSetChanged();
    }



    private void IniciarComponentes(){
        ListScenesCampaign = findViewById(R.id.ListScenesCampaign);

    }

    private void IniciarEventos(){
        escenas = new ArrayList<>();
        nEscenas = new ArrayList<>();

        try {

            escenas = SceneController.listarEscenas(getApplicationContext());

            for(Escenas escenas: escenas){
                nEscenas.add(escenas.getNombreEscena());
            }
            adaScene = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nEscenas);
            ListScenesCampaign.setAdapter(adaScene);

        } catch (Exception ex){
            Toast.makeText(this, "Error : "+ex, Toast.LENGTH_SHORT).show();
        }

        ListScenesCampaign.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        ListScenesCampaign.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int itemSeleccionadoEnPosicion;

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                itemSeleccionadoEnPosicion = i;
                actionMode.setTitle("Opciones");
                actionMode.setSubtitle("Para " + escenas.get(i).getNombreEscena().toString());

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
                String gremio = nEscenas.get(itemSeleccionadoEnPosicion);
                int id = menuItem.getItemId();
                Escenas mg = buildScene(gremio);

                switch (id){
                    case R.id.opcion_Editar:
                        Toast.makeText(ListarScenes.this, ""+gremio, Toast.LENGTH_SHORT).show();
                        iniciarActividad(ViewScenes.class);
                        break;
                    case R.id.opcion_Eliminar:
                        try {
                            SceneController.removeScene(getApplicationContext(),mg);
                            iniciarActividad(VistaAllGuilds.class);
                        }catch (Exception ex){
                            Toast.makeText(ListarScenes.this, "error "+ex, Toast.LENGTH_SHORT).show();
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

        ListScenesCampaign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name= nEscenas.get(i);

                try {
                    int iDScene = SceneController.findSceneByName(getApplicationContext(), name);
                    Intent intent = new Intent(getApplicationContext(), ViewScenes.class);
                    intent.putExtra("IDGUILD",iDScene);
                    startActivity(intent);

                }catch (Exception ex){
                    Toast.makeText(ListarScenes.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}