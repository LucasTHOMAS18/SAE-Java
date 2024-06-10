package fr.aftek;
import java.util.List;
import java.util.Objects;

import fr.GenderException;

public class Athlete {
    private String nom;
    private String prenom;
    private char sexe;
    private float force;
    private float agilite;
    private float endurance;
    private Pays pays;
    private Equipe equipe;
    private List<Epreuve> epreuves;


    public Athlete(String nom, String prenom, char sexe, float force, float agilite, float endurance, Pays pays, Equipe equipe, List<Epreuve> epreuves) throws GenderException {
        if(sexe != 'F' && sexe != 'M') throw new GenderException();
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
        this.pays = pays;
        this.pays.ajouteAthlete(this);
        this.equipe = equipe;
        this.epreuves = epreuves;
    }

    public Athlete(String nom, String prenom, char sexe, float force, float agilite, float endurance, Pays pays) throws GenderException {
        if(sexe != 'F' && sexe != 'M') throw new GenderException();
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
        this.pays = pays;
        this.pays.ajouteAthlete(this);
    }

    public Athlete(String nom, String prenom, char sexe, Pays pays) throws GenderException{
        if(sexe != 'F' && sexe != 'M') throw new GenderException();
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.pays = pays;
        this.pays.ajouteAthlete(this);
        this.force = (float)(Math.random() * 20);
        this.agilite = (float)(Math.random() * 20);
        this.endurance = (float)(Math.random() * 20);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public char getSexe() {
        return sexe;
    }

    public float getForce() {
        return force;
    }

    public float getAgilite() {
        return agilite;
    }

    public float getEndurance() {
        return endurance;
    }

    public Pays getPays() {
        return pays;
    }

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void ajouteEpreuve(Epreuve e){
        this.epreuves.add(e);
    }

    public void retireEpreuve(Epreuve e){
        if(!this.epreuves.contains(e)) this.epreuves.remove(e);
    }

    public Equipe getEquipe() {
        return equipe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Athlete)) {
            return false;
        }
        Athlete athlete = (Athlete) o;
        return nom.equals(athlete.nom) && 
        prenom.equals(athlete.prenom) && 
        sexe == athlete.sexe && 
        force == athlete.force && 
        agilite == athlete.agilite && 
        endurance == athlete.endurance && 
        pays.equals(athlete.pays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, sexe, force, agilite, endurance, pays);
    }

    @Override
    public String toString() {
        return this.prenom + 
        " " + 
        this.nom + 
        (this.sexe == 'F' ? " femme " : " homme ") + 
        "qui participe pour " + 
        this.pays + 
        " force: " + 
        this.force + 
        " agilite: " +
        this.agilite + 
        " endurance: " + 
        this.endurance;
    }
}
