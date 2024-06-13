package fr.aftek;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe Pays, représente un pays participant aux JO
 */
public class Pays {
    private String nom;
    private List<Athlete> athletes;
    private List<Equipe> equipes;

    /**
     * Constructeur de la classe Pays
     * @param nom le nom du pays
     */
    public Pays(String nom) {
        this.nom = nom;
        this.athletes = new ArrayList<>();
    }

    /**
     * Constructeur de la classe Pays
     * @param nom le nom du pays
     * @param athletes la liste des athlètes du pays
     */
    public Pays(String nom, List<Athlete> athletes) {
        this.nom = nom;
        this.athletes = athletes;
    }

    /**
     * Constructeur de la classe Pays
     * @param nom le nom du pays
     * @param athletes la liste des athlètes du pays
     * @param equipes la liste des équipes du pays
     */
    public void ajouteAthlete(Athlete athlete) {
        this.athletes.add(athlete);
    }

    /**
     * Ajoute une équipe à la liste des équipes du pays
     * @param equipe l'équipe à ajouter
     */
    public void ajouteEquipe(Equipe equipe) {
        this.equipes.add(equipe);
    }

    /**
     * Retourne le nom du pays
     * @return le nom du pays
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la liste des athlètes du pays
     * @return la liste des athlètes du pays
     */
    public List<Athlete> getAthletes() {
        return athletes;
    }

    /**
     * Retourne la liste des équipes du pays
     * @return la liste des équipes du pays
     */
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
    public int hashCode() {
        return Objects.hash(this.nom);
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
