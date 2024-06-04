package fr.aftek;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipe {
    private String nom;
    private Pays pays;
    private List<Athlete> athletes;

    public Equipe(Pays pays) {
        this.nom = pays.getNom();
        this.pays = pays;
        this.athletes = new ArrayList<Athlete>();
    }

    public Equipe(String pays){
        this.pays = new Pays(pays);
        this.nom = this.pays.getNom();
        this.athletes = new ArrayList<Athlete>();
    }

    public Equipe(String nom, Pays pays) {
        this.nom = nom;
        this.pays = pays;
        this.athletes = new ArrayList<Athlete>();
    }

    public Equipe(String nom, String pays){
        this.nom = nom;
        this.pays = new Pays(pays);
        this.athletes = new ArrayList<Athlete>();
    }

    public void ajouteAthlete(Athlete athlete) {
        this.athletes.add(athlete);
    }

    public void retireAthlete(Athlete athlete) {
        this.athletes.remove(athlete);
    }

    public String getNom() {
        return nom;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public Pays getPays() {
        return pays;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Equipe)) {
            return false;
        }
        Equipe equipe = (Equipe) o;
        return Objects.equals(nom, equipe.nom) && Objects.equals(pays, equipe.pays) && Objects.equals(athletes, equipe.athletes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, pays, athletes);
    }
    
}
