package com.example.cafoma_app.vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.entite.Formation;

public class FormulaireActivity extends AppCompatActivity {
    private static String TAG = "FormulaireActivity";
    private EditText numId;
    private EditText coutId;
    private EditText imageId;
    private EditText nomId;
    private EditText descriptionId;
    private Button btnEnregistrer;
    private ControleurBdd controleurBdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        controleurBdd = ControleurBdd.getInstance(this);
        initialisationIhm();
    }

    private void initialisationIhm() {
        numId = (EditText) findViewById(R.id.editNum);
        coutId = (EditText) findViewById(R.id.editMontant);
        imageId = (EditText) findViewById(R.id.editDescription);
        nomId = (EditText) findViewById(R.id.editNom);
        descriptionId = (EditText) findViewById(R.id.editDescription);
        btnEnregistrer = (Button) findViewById(R.id.btnEnregistrer);
        ecouteurEnregistrer();
    }

    private void ecouteurEnregistrer() {
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Formation formation = creerFacture();
                if(formation != null){
                    controleurBdd.persisterFacture(formation);
                    Intent intent = new Intent(FormulaireActivity.this, ListeActivity.class);
                    intent.putExtra("mode", 0);
                    startActivity(intent);
                }
            }
        });
    }
    private Formation creerFacture(){
        Formation formation = null;
        int id = Integer.parseInt(numId.getText().toString());
        int cout = Integer.parseInt(coutId.getText().toString());
        String date = imageId.getText().toString();
        String nom = nomId.getText().toString();
        String description = descriptionId.getText().toString();
        if (date.isEmpty() || nom.isEmpty() || description.isEmpty()) {
            Toast.makeText(FormulaireActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
        } else {
            formation = new Formation(id, cout, date, nom, description);
            Log.i(TAG, "formation=" + formation.toString());
        }
        return formation;
    }
}