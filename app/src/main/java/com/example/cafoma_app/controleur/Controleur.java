package com.example.cafoma_app.controleur;

import com.example.cafoma_app.entite.Formation;

import java.util.List;

public interface Controleur {
    List<Formation> getFormationList();
    void setFormation(Formation formation);
    Formation getFormation();
}
