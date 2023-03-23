package com.example.cafoma_app.entite;

import java.util.List;

public class User {
    private String login;
    private String password;
    private String mail;
    private String role;
    private String image;
    private String valide;
    private List<Formation> formationInscrit;

    public User(String login, String password, String mail, String role, String image) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.image = image;
    }
    public User(String login, String valide) {
        this.login = login;
        this.valide = valide;
    }

    public User(String login, String verif, List<Formation> formationInscrit) {
        this.valide = verif;
        this.login = login;
        this.formationInscrit=formationInscrit;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", valide='" + valide +
                ", Formations='" + afficheShortFormation(formationInscrit) +
                '}';
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getValide() { return valide; }
    public void setValide(String valide) { this.valide = valide; }

    public List<Formation> getFormationList(){
        return formationInscrit;
    }

    public String afficheShortFormation (List<Formation> formations){
        String affiche = "";
        if (formations != null){
            for(Formation f : formations){
                affiche = affiche+f.toStringShort();
            }
        }
        return affiche;
    }
}
