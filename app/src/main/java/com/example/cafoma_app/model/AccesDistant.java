package com.example.cafoma_app.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cafoma_app.controleur.Controleur;
import com.example.cafoma_app.controleur.ControleurServeur;
import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.Ressource;
import com.example.cafoma_app.entite.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesDistant implements ReponseAsyncItf {
    private final static String TAG = "AccesDistant";
    private static final String SERVERADDR = "http://10.0.2.2/formation/formation.rest.php";
    private ControleurServeur controleurServeur;
    public AccesDistant(){
        controleurServeur = ControleurServeur.getInstance();
    }
    public Intent intent;
    public Activity activity;

    public AccesDistant(Intent intent, Activity activity){
        super();
        this.intent = intent;
        this.activity = activity;
        controleurServeur = ControleurServeur.getInstance();
    }
    @Override
    public void reponseRequete(String reponse) {
        String[] message = reponse.split("#");
        if(message.length > 1) {
            if (message[0].equals("lister")) {
                try {
                    JSONArray jsonTabFormation = new JSONArray(message[1]);
                    List<Formation> formationList = parserFormationList(jsonTabFormation);
                    controleurServeur.setFormationList(formationList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
            if (message[0].equals("ressource")) {
                try {
                    JSONArray jsonTabRessource = new JSONArray(message[1]);
                    List<Ressource> ressourceList = parserRessourceList(jsonTabRessource);
                    controleurServeur.setRessourceList(ressourceList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
            if (message[0].equals("mdp")) {
                try {
                    JSONObject jsonTabMdp = new JSONObject(message[1]);
                    User user = parserUserList(jsonTabMdp);
                    controleurServeur.setUser(user);
                    if (user.getValide().equals("vrai")){
                        activity.startActivity(intent);
                    }
                    else{
                        Toast.makeText(activity, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (message[0].equals("erreur")) {
                Log.i(TAG,"Erreur : " + message[1]);
            }
        }
    }
    public void envoyerRequete(String operation){
        RequeteHttp requeteHttp = new RequeteHttp();
        requeteHttp.reponseAsync = this;
        requeteHttp.addParam("operation", operation);
        requeteHttp.execute(SERVERADDR);
    }
    public void envoyerRequeteView(String operation, View view){
        RequeteHttp requeteHttp = new RequeteHttp(view);
        requeteHttp.reponseAsync = this;
        requeteHttp.addParam("operation", operation);
        requeteHttp.execute(SERVERADDR);
    }
    private List<Formation> parserFormationList(JSONArray jsonTabFormation) throws JSONException {
        List<Formation> formationList = new ArrayList<>();
        Formation formation = null;
        for (int i=0; i<jsonTabFormation.length(); i++){
            int id = jsonTabFormation.getJSONObject(i).getInt("id");
            int cout = jsonTabFormation.getJSONObject(i).getInt("cout");
            String image = jsonTabFormation.getJSONObject(i).getString("image");
            String nom = jsonTabFormation.getJSONObject(i).getString("nom");
            String description = jsonTabFormation.getJSONObject(i).getString("description");
            formation = new Formation(id,cout,image,nom,description);
            formationList.add(formation);
        }
        return formationList;
    }
    private List<Ressource> parserRessourceList(JSONArray jsonTabRessource) throws JSONException {
        List<Ressource> ressourceList = new ArrayList<>();
        Ressource ressource = null;
        for (int i=0; i<jsonTabRessource.length(); i++){
            int idRessource = jsonTabRessource.getJSONObject(i).getInt("idRessource");
            int idFormation = jsonTabRessource.getJSONObject(i).getInt("idFormation");
            String description = jsonTabRessource.getJSONObject(i).getString("description");
            String ressources = jsonTabRessource.getJSONObject(i).getString("ressource");
            ressource = new Ressource(idRessource,idFormation,description,ressources);
            ressourceList.add(ressource);
        }
        return ressourceList;
    }/*
    private User parserUserList(JSONObject jsonUser) throws JSONException {
        User user = null;
        String login = jsonUser.getString("login");
        String verif = jsonUser.getString("verif");
        user = new User(login,verif);
        return user;
    }*/
    private User parserUserList(JSONObject jsonUser) throws JSONException {
        User user = null;
        List<Formation> formations = null;
            String login = jsonUser.getString("login");
            String verif = jsonUser.getString("verif");
            formations = parserFormationList(jsonUser.getJSONArray("formations"));
            if (jsonUser.getJSONArray("formations").getJSONObject(0).getInt("id") == -1){
                user = new User(login,verif);
            }
            else{

                user = new User(login,verif,formations);
            }
        return user;
    }
}
