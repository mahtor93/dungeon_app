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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.GuildController;
import com.example.creacindepersonaje.Controller.PersonajeController;
import com.example.creacindepersonaje.Modelos.Personaje;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class ViewGuild extends AppCompatActivity {

    private TextView tvGuildName;
    private ListView lvIntegrantes;
    private ArrayList<Personaje> guildIntegrantes;
    private ArrayList<String> integrantesNames;
    private ArrayAdapter<String> adapterGuild;
    private Button bt_editarGuild;

    private int idPj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guild);
        iniciarComponentes();
        iniciarEventos();
    }



    private void iniciarComponentes(){
        bt_editarGuild = findViewById(R.id.bt_editarGuild);
        tvGuildName = findViewById(R.id.tvGuildName);
        lvIntegrantes = findViewById(R.id.lvIntegrantes);
    }



    private void iniciarEventos(){
        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        int ID = extra.getInt("IDGUILD");
        try {
            tvGuildName.setText(GuildController.findGuildById(getApplicationContext(), ID));
        }catch (Exception ex){
            Toast.makeText(this, "Error: "+ex, Toast.LENGTH_SHORT).show();
        }

        integrantesNames = new ArrayList<>();
        try {
            guildIntegrantes = GuildController.listarIntegrantes(getApplicationContext(), ID);
        }catch (Exception ex){
            Toast.makeText(this, "Error :"+ex, Toast.LENGTH_SHORT).show();
        }

        for(Personaje p : guildIntegrantes){
            integrantesNames.add(p.getNombre());
        }

        adapterGuild = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,integrantesNames);
        lvIntegrantes.setAdapter(adapterGuild);
        adapterGuild.notifyDataSetChanged();
        lvIntegrantes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvIntegrantes.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int itemSeleccionadoEnPosicion;
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                itemSeleccionadoEnPosicion = i;
                actionMode.setTitle("Quitar a "+integrantesNames.get(i).toString());
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.remove_guild,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                String name = integrantesNames.get(itemSeleccionadoEnPosicion);
                try{
                   idPj = PersonajeController.getIdPorNombre(getApplicationContext(),name);
                }catch (Exception ex){
                    Toast.makeText(ViewGuild.this, "error getIdPorNombre: "+ex, Toast.LENGTH_SHORT).show();
                }
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.mnRemove:
                        try{
                            GuildController.removePersonaje(getApplicationContext(),idPj);
                            adapterGuild.remove(name);
                            closeContextMenu();
                        }catch (Exception ex){
                            Toast.makeText(ViewGuild.this, "Error removePersonaje "+ex, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
                return true;

            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });


        bt_editarGuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), edit_guild.class);
                i.putExtra("ID_GUILD",ID);
                startActivity(i);
            }
        });


    }
}