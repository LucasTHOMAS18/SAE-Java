package fr.aftek;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pays {
    public static Set<Pays> PAYS = new HashSet<>();
    private String nom;
    private List<Athlete> athletes;
    private List<Equipe> equipes;

    public Pays(String nom) {
        this.nom = nom;
        this.athletes = new ArrayList<>();
        PAYS.add(this);
    }

    public Pays(String nom, List<Athlete> athletes) {
        this.nom = nom;
        this.athletes = athletes;
        PAYS.add(this);
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

    public static Pays get(String nom){
        for (Pays pays : PAYS) {
            if(pays.getNom().equals(nom)) return pays;
        }
        Pays pays = new Pays(nom);
        PAYS.add(pays);
        return pays;
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
