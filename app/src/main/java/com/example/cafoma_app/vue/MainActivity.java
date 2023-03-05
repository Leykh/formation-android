package com.example.cafoma_app.vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.controleur.ControleurServeur;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ImageButton btnMonFormulaire;
    private ImageButton btnListeBdd;
    private ImageButton btnListeServeur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControleurBdd.getInstance(this);
        ControleurServeur.getInstance();
        initialisationIhm();
    }
    private void initialisationIhm() {
        btnMonFormulaire = (ImageButton)findViewById(R.id.btnFormulaire);
        ecouterbtnFormulaire();
        btnListeBdd = (ImageButton)findViewById(R.id.btnListeBdd);
        ecouterListeBd();
        btnListeServeur = (ImageButton)findViewById(R.id.btnListeServeur);
        ecouterListeServeur();
    }
    private void ecouterbtnFormulaire(){
        btnMonFormulaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Evt formulaire");
                Intent intent = new Intent(MainActivity.this, FormulaireActivity.class);
               startActivity(intent);
            }
        });
    }
    private void ecouterListeBd(){
        btnListeBdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Evt liste Bdd");
                Intent intent = new Intent(MainActivity.this, ListeActivity.class);
                intent.putExtra("mode", 0); // mode bdd
                startActivity(intent);
            }
        });
    }
    private void ecouterListeServeur(){
        btnListeServeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Evt liste Serveur");
                Intent intent = new Intent(MainActivity.this, ListeActivity.class);
                intent.putExtra("mode", 1); // mode seveur
                startActivity(intent);
            }
        });
    }

}