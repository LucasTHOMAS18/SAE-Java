package fr.aftek;

import java.util.ArrayList;
import java.util.List;

public class Pays {
    private String nom;
    private List<Athlete> athletes;
    private List<Equipe> equipes;

    public Pays(String nom) {
        this.nom = nom;
        this.athletes = new ArrayList<>();
    }

    public Pays(String nom, List<Athlete> athletes) {
        this.nom = nom;
        this.athletes = athletes;
    }

    public void ajouteAthlete(Athlete athlete) {
        this.athletes.add(athlete);
    }

    public void ajouteEquipe(Equipe equipe) {
        this.equipes.add(equipe);
    }

    public String getNom() {
        return nom;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pays)) {
            return false;
        }
        Pays pays = (Pays) o;
        return this.getNom().equals(pays.getNom());
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
