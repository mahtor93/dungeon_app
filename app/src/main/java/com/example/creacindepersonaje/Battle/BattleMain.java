package com.example.creacindepersonaje.Battle;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.BattleController;
import com.example.creacindepersonaje.Modelos.Battle;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class BattleMain extends AppCompatActivity {

    private Button btnOrganizarBatalla;
    private Button btnHistoria;
    private ListView lvBatallas;

    private ArrayList<Battle> getterBattles;
    private ArrayList<String> battles;
    private ArrayAdapter<String> adapterBattles;
    private Battle battle = new Battle();
    private int idBattleEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);
        iniciarComponentes();
        iniciarEventos();

    }
    private void iniciarActividad(Class clase){
        Intent intent = new Intent(this,clase);
        startActivity(intent);
    }
    public  void iniciarComponentes(){
        lvBatallas = findViewById(R.id.lvBatallas);
        btnHistoria = findViewById(R.id.btnHistoria);
        btnOrganizarBatalla = findViewById(R.id.btnOrganizarBatalla);

    }

    public void iniciarEventos(){
        battles = new ArrayList<>();
        try {
            getterBattles = BattleController.listarBatallas(getApplicationContext());
            for(Battle bat : getterBattles){
                battles.add(bat.getNameBattle());
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error "+ex, Toast.LENGTH_SHORT).show();
        }
        //Obtenemos Batallas desde un controller



        adapterBattles = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,battles);
        lvBatallas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvBatallas.setAdapter(adapterBattles);

        lvBatallas.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            private int itemSelected;
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                itemSelected = i;
                actionMode.setTitle("Opciones");
                actionMode.setTitle("Para batalla");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.menu_campaign,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                int id = menuItem.getItemId();
                try {

                    int idBattle = BattleController.getIdByName(getApplicationContext(), battles.get(itemSelected));

                switch (id){
                    case R.id.opcion_Editar:
                        // Conectar con el editor de batallas.
                        try {
                            idBattleEdit = BattleController.getIdByName(getApplicationContext(), battles.get(itemSelected));
                            Intent intent = new Intent(getApplicationContext(), battleEditor.class);
                            intent.putExtra("IDBATTLE", idBattleEdit);
                            Toast.makeText(BattleMain.this, "idBattleEdit "+idBattleEdit, Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }catch (Exception ex){
                            Toast.makeText(BattleMain.this, "Error edit "+ex, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.opcion_Eliminar:
                            //Toast.makeText(BattleMain.this, ""+idBattle, Toast.LENGTH_SHORT).show();
                            adapterBattles.remove(battles.get(itemSelected));
                            BattleController.removeBattle(getApplicationContext(), idBattle);

                        break;

                }}catch (Exception ex){
                    Toast.makeText(BattleMain.this, "Error Context Menu "+ex, Toast.LENGTH_SHORT).show();
                }

                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        lvBatallas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Se obtiene ID por nombre y se lanza la siguiente actividad con un intent put extra.
                Battle bat = new Battle();
                int batId=-1;
                int guildId=-1;
                try {
                    batId = BattleController.getIdByName(getApplicationContext(),lvBatallas.getItemAtPosition(i).toString());
                    battle = BattleController.getBattleById(getApplicationContext(),batId);
                    guildId = battle.getIdGuild();
                }catch (Exception ex){
                    Toast.makeText(BattleMain.this, "Error "+ex, Toast.LENGTH_SHORT).show();
                }
                if(guildId==-1){
                    //Crear vista para añadir guild, en el editor puede quitarse la guild.
                    Intent intent = new Intent(getApplicationContext(), BattleAddGuild.class);
                    intent.putExtra("IDBATTLE",batId);
                    startActivity(intent);
                }
                if (guildId != -1) {
                    Intent intent = new Intent(getApplicationContext(),JugadaAtaque.class);
                    intent.putExtra("IDBATTLE",batId);
                    intent.putExtra("IDGUILD",guildId);
                    startActivity(intent);
                }

            }
        });


        btnOrganizarBatalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(CreateBattle.class);
            }
        });

        btnHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), selectBattleHistory.class);
                startActivity(intent);
            }
        });

    }

}