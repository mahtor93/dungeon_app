package com.example.creacindepersonaje.Campaign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.CampaignController;
import com.example.creacindepersonaje.Controller.GuildController;
import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.Modelos.modelGuild;
import com.example.creacindepersonaje.R;

import java.util.ArrayList;

public class EditarCampaign extends AppCompatActivity {
    private TextView tv_camp_edit,tvEditRelato;
    private EditText txb_nombreEditcamp;
    private Button bt_editaName, bt_Remove;
    private ListView lvChangeGuilds;

    private ArrayList<String> guilds;
    private ArrayList<modelGuild> guildList;
    private ArrayAdapter<String> adapterGuilds;

    int id_camp;
    int idGuild;
    int getIdGuildOriginal;
    Campaing c = new Campaing();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_campaign);
        iniciarComponentes();
        iniciarEventos();
    }
    public void iniciarComponentes() {
        tv_camp_edit = findViewById(R.id.tv_nomb_camp_Edit);
        bt_editaName = findViewById(R.id.bt_crearScene);
        bt_Remove = findViewById(R.id.bt_removeCamp);
        txb_nombreEditcamp = findViewById(R.id.tv_nameScene);
        tvEditRelato = findViewById(R.id.tvEditRelato);
        lvChangeGuilds = findViewById(R.id.lvChangeGuilds);
    }
    public void iniciarEventos() {
        try {
            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            int ID = extra.getInt("ID");
            c = CampaignController.findCampaignById(getApplicationContext(), ID);
            id_camp = c.getIdCampaign();
            tv_camp_edit.setText(c.getNombreCampaing() + "");
            getIdGuildOriginal = c.getIdGuild();

        } catch (Exception ex) {
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_SHORT).show();
        }

        try{
            guilds = new ArrayList<>();
            guildList = GuildController.listarGuilds(getApplicationContext());
            for(modelGuild guild : guildList){
                guilds.add(guild.getGuildName());
            }
        }catch (Exception ex){
            Toast.makeText(this, "Error listarGuilds "+ex, Toast.LENGTH_SHORT).show();
        }

        adapterGuilds = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, guilds);
        lvChangeGuilds.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvChangeGuilds.setAdapter(adapterGuilds);




        lvChangeGuilds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    idGuild = GuildController.findGuildByName(getApplicationContext(),guilds.get(i));
                    Toast.makeText(EditarCampaign.this, "idGuild Selected "+idGuild, Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(EditarCampaign.this, "Error findGuildByName "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_editaName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditarCampaign.this, "idGuild to edit "+idGuild, Toast.LENGTH_SHORT).show();
                if(txb_nombreEditcamp.getText().length()==0&&tvEditRelato.getText().length()==0&&idGuild==0){
                    Toast.makeText(EditarCampaign.this, "No hay nada para editar", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        String nameCamp = txb_nombreEditcamp.getText().toString();
                        c = CampaignController.findCampaignById(getApplicationContext(), id_camp);
                        c.setNombreCampaing(nameCamp);
                        c.setRelatoCampaign(tvEditRelato.getText().toString());
                        c.setIdGuild(idGuild);
                        CampaignController.renameCampaign(getApplicationContext(), c);
                        Toast.makeText(EditarCampaign.this, "Editado con éxito", Toast.LENGTH_SHORT).show();
                        Intent i2 = new Intent(getApplicationContext(), ListarCampaign.class);
                        startActivity(i2);
                    } catch (Exception ex) {
                        Toast.makeText(EditarCampaign.this, "Error" + ex, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        bt_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irem = new Intent(getApplicationContext(), RemoveCamp.class);
                irem.putExtra("ID", id_camp);
                Toast.makeText(EditarCampaign.this, "La ID A BORRAR : " +id_camp, Toast.LENGTH_SHORT).show();
                startActivity(irem);
            }
        });
    }
}