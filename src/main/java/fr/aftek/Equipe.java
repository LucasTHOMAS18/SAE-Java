package fr.aftek;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipe {
    private String nom;
    private Pays pays;
    private List<Athlete> athletes;

    /**
     * Constructeur pour initialiser une équipe avec un pays.
     *
     * @param pays Le pays de l'équipe.
     */
    public Equipe(Pays pays) {
        this.nom = pays.getNom();
        this.pays = pays;
        this.athletes = new ArrayList<Athlete>();
    }

    /**
     * Constructeur pour initialiser une équipe avec un nom de pays.
     *
     * @param pays Le nom du pays de l'équipe.
     */
    public Equipe(String pays){
        this.pays = new Pays(pays);
        this.nom = this.pays.getNom();
        this.athletes = new ArrayList<Athlete>();
    }

    /**
     * Constructeur pour initialiser une équipe avec un nom et un pays.
     *
     * @param nom Le nom de l'équipe.
     * @param pays Le pays de l'équipe.
     */
    public Equipe(String nom, Pays pays) {
        this.nom = nom;
        this.pays = pays;
        this.athletes = new ArrayList<Athlete>();
    }

    /**
     * Constructeur pour initialiser une équipe avec un nom et un nom de pays.
     *
     * @param nom Le nom de l'équipe.
     * @param pays Le nom du pays de l'équipe.
     */
    public Equipe(String nom, String pays){
        this.nom = nom;
        this.pays = new Pays(pays);
        this.athletes = new ArrayList<Athlete>();
    }

    /**
     * Ajoute un athlète à l'équipe.
     *
     * @param athlete L'athlète à ajouter.
     */
    public void ajouteAthlete(Athlete athlete) {
        this.athletes.add(athlete);
    }

    /**
     * Retire un athlète de l'équipe.
     *
     * @param athlete L'athlète à retirer.
     */
    public void retireAthlete(Athlete athlete) {
        this.athletes.remove(athlete);
    }

    /**
     * @return Le nom de l'équipe.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return La liste des athlètes de l'équipe.
     */
    public List<Athlete> getAthletes() {
        return athletes;
    }

    /**
     * @return Le pays de l'équipe.
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * @return Le sport de l'équipe.
     */
    public Sport getSport() {
        return athletes.get(0).getSport();
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
