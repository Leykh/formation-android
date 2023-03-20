package com.example.cafoma_app.vue;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafoma_app.R;
import com.example.cafoma_app.controleur.ControleurBdd;
import com.example.cafoma_app.controleur.ControleurServeur;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private Button button_login_login;
    private EditText editText_login_username;
    private EditText editText_login_password;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControleurBdd.getInstance(this);
        ControleurServeur.getInstance();
        initialisationIhm();
    }
    private void initialisationIhm() {

        editText_login_username = (EditText) findViewById(R.id.editText_login_username);
        editText_login_password = (EditText) findViewById(R.id.editText_login_password);
        button_login_login = (Button) findViewById(R.id.button_login_login);
        ecouterListeServeur();
    }
    private void ecouterListeServeur(){
        button_login_login.setOnClickListener(new View.OnClickListener() {
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