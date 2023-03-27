package com.example.demineur;

import java.io.Serializable;

public class Profil implements Serializable {

    private  String nom;
    private  String prenom;
    private  int score;
    private Difficulte difficulte;

    Profil(String nom, String lastName,Difficulte difficulte,int score) {
        this.nom = nom;
        this.prenom = prenom;
        this.difficulte = difficulte;
        this.score = score;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDifficulte(Difficulte difficulte) {
        this.difficulte = difficulte;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getScore() {
        return score;
    }

    public Difficulte getDifficulte() {
        return difficulte;
    }
}
