package com.example.creacindepersonaje.Campaign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.R;
import com.example.creacindepersonaje.Monstruo.VistaAllMonsters;

import java.util.ArrayList;

public class MainCampaign extends AppCompatActivity {


    private Button bt_addCampaign;
    private Button bt_listCampaign;



    private void iniciarActividad(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_campaign);
        IniciarComponentes();
        IniciarEventos();



    }



    private void IniciarComponentes(){


        bt_addCampaign = findViewById(R.id.bt_addCampaign);
        bt_listCampaign = findViewById(R.id.bt_listCampaign);

    }

    private void IniciarEventos(){

        bt_addCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividad(AddCampaign.class);
            }
        });

        bt_listCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    iniciarActividad(ListarCampaign.class);}
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Error : "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}