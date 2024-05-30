package com.example.creacindepersonaje.Campaign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.creacindepersonaje.BDConnection.DBConnection;
import com.example.creacindepersonaje.Controller.CampaignController;
import com.example.creacindepersonaje.R;
import com.example.creacindepersonaje.Modelos.Campaing;

import java.util.ArrayList;

public class ListarCampaign extends AppCompatActivity {

    private ListView ListCampaign;
    private ArrayList<Campaing> campaingID;
    private ArrayList<String> nomCampaigns;
    private ArrayAdapter<String> adaCampaigns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_campaign);
        IniciarComponentes();
        IniciarEventos();

    }

    private void IniciarComponentes() {
        ListCampaign = findViewById(R.id.ListScenesCampaign);

    }

    private void IniciarEventos() {

        nomCampaigns = new ArrayList<>();
        campaingID = new ArrayList<>();


        try {
            DBConnection con = new DBConnection(getApplicationContext(), "base1", null, 1);
            SQLiteDatabase db = con.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT  idCampaign, nombreCampaign FROM campaign", null);
            String textoCam;
            if (cur.moveToFirst()) {
                do {
                    Campaing c = new Campaing();
                    c.setIdCampaign(cur.getInt(0));
                    campaingID.add(c);

                    textoCam = cur.getString(0) + "," + cur.getString(1);
                    nomCampaigns.add(textoCam);

                } while (cur.moveToNext());
            }

            adaCampaigns = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nomCampaigns);
            ListCampaign.setAdapter(adaCampaigns);

        } catch (Exception ex) {
            Toast.makeText(this, "Error" + ex, Toast.LENGTH_SHORT).show();
        }


        ListCampaign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                try {
                    int idCAM = campaingID.get(i).getIdCampaign();
                    Intent verCamp = new Intent(getApplicationContext(), VerCampaign.class);
                    verCamp.putExtra("ID", idCAM);
                    //Toast.makeText(ListarCampaign.this, "ID extra" + idCAM, Toast.LENGTH_SHORT).show();
                    startActivity(verCamp);
                } catch (Exception ex) {
                    Toast.makeText(ListarCampaign.this, "Error: " + ex, Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


}