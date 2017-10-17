package com.example.benjaminlouis.tp12app;

/**
 * Created by benjaminlouis on 16/10/2017.
 */

public class User {
    private String nom;
    private String prenom;
    private String metier;
    private Integer age;
    private Integer id;

    public User(Integer id,String nom, String prenom, Integer age, String metier) {
        this.nom = nom;
        this.prenom = prenom;
        this.metier = metier;
        this.age = age;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
