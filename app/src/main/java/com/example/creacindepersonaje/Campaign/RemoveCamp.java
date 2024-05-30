package com.example.creacindepersonaje.Campaign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.creacindepersonaje.Controller.CampaignController;
import com.example.creacindepersonaje.Modelos.Campaing;
import com.example.creacindepersonaje.R;

public class RemoveCamp extends AppCompatActivity {

    private Button bt_confirm;
    int idC;
    Campaing c = new Campaing();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_camp);
        iniciarComp();
        iniciarEve();
    }

    private void iniciarComp() {
        bt_confirm = findViewById(R.id.bt_confirma_remov);
    }

    private void iniciarEve() {
        try {
            Intent intent = this.getIntent();
            Bundle datos = intent.getExtras();
            idC = datos.getInt("ID");
            c = CampaignController.findCampaignById(getApplicationContext(), idC);

        } catch (Exception ex) {
            Toast.makeText(this, "Error " + ex, Toast.LENGTH_SHORT).show();

        }

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (CampaignController.removeCampaign(getApplicationContext(), c.getIdCampaign())){

                        Toast.makeText(RemoveCamp.this, "Eliminada", Toast.LENGTH_SHORT).show();
                        Intent i3 = new Intent(getApplicationContext(), ListarCampaign.class);
                        startActivity(i3);

                    }
                } catch (Exception ex) {
                    Toast.makeText(RemoveCamp.this, "ERROR" + ex, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}