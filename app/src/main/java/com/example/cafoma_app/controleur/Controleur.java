package com.example.cafoma_app.controleur;

import com.example.cafoma_app.entite.Formation;
import com.example.cafoma_app.entite.Ressource;

import java.util.List;

public interface Controleur {
    List<Formation> getFormationList();
    List<Ressource> getRessourceList();
    void setFormation(Formation formation);
    Formation getFormation();
    Ressource getRessource();

    void setRessource(Ressource ressource);
}
