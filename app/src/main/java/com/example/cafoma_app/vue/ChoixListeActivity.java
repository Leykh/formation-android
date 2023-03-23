package com.example.cafoma_app.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurServeur;

public class ChoixListeActivity extends AppCompatActivity {
    private static String TAG = "ChoixActivity";
    private int mode;
    private String titre;
    private Controleur controleur;
    private TextView titreViewFormationsListe;
    private TextView titreViewMesFormations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);
        controleur = ControleurServeur.getInstance();
        titreViewFormationsListe = (TextView) findViewById(R.id.titreId);
        titreViewMesFormations = (TextView) findViewById(R.id.titreId2);
        initChoix();

    }
    private void initChoix(){

        titreViewFormationsListe.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ChoixListeActivity.this, ListeActivity.class);
            intent.putExtra("mode", 0);
            startActivity(intent);
        }
    });
        titreViewMesFormations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoixListeActivity.this, ListeActivity.class);
                intent.putExtra("mode", 1);
                startActivity(intent);
            }
        });
    }
}
