package com.example.creacindepersonaje.Campaign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.CampaignController;
import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.R;

public class VerCampaign extends AppCompatActivity {

    private TextView tv_nomCamp,tvRelato;
    int id_camp;
    Campaing c = new Campaing();
    private Button btn_vistaEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_campaign);
        iniciarComponentes();
        iniciarEventos();
    }

    private void iniciarComponentes() {
        tv_nomCamp = findViewById(R.id.tv_nombreCamp);
        tvRelato = findViewById(R.id.tvRelato);
        btn_vistaEditar = findViewById(R.id.bt_editRemoveCamp);
    }

    private void iniciarEventos() {
        try {
            Intent intent = this.getIntent();
            Bundle extra = intent.getExtras();
            int ID = extra.getInt("ID");
            c = CampaignController.findCampaignById(getApplicationContext(), ID);
            id_camp = c.getIdCampaign();
            tvRelato.setText(c.getRelatoCampaign()+"");
            tv_nomCamp.setText(c.getNombreCampaing()+"");

        } catch (Exception ex) {
            Toast.makeText(this, "Error" +ex, Toast.LENGTH_SHORT).show();
        }

        btn_vistaEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Intent verCamp = new Intent(getApplicationContext(), EditarCampaign.class);
                    verCamp.putExtra("ID", id_camp);
                    //Toast.makeText(getApplicationContext(), "ID extra" + id_camp, Toast.LENGTH_SHORT).show();
                    startActivity(verCamp);
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Error: " + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}