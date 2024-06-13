package fr.aftek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * La classe Athlete représente un athlète avec ses caractéristiques et ses associations.
 * Chaque athlète appartient à un pays et peut participer à différentes épreuves.
 */
public class Athlete {
    private String nom;
    private String prenom;
    private char sexe;
    private int force;
    private int agilite;
    private int endurance;
    private Pays pays;
    private Equipe equipe;
    private List<Epreuve> epreuves;

    /**
     * Constructeur complet pour la classe Athlete.
     *
     * @param nom Le nom de l'athlète.
     * @param prenom Le prénom de l'athlète.
     * @param sexe Le sexe de l'athlète ('M' pour masculin, 'F' pour féminin).
     * @param force La force de l'athlète.
     * @param agilite L'agilité de l'athlète.
     * @param endurance L'endurance de l'athlète.
     * @param pays Le pays de l'athlète.
     * @param equipe L'équipe de l'athlète.
     * @param epreuves La liste des épreuves auxquelles l'athlète participe.
     * @throws GenderException Si le sexe n'est pas 'M' ou 'F'.
     */
    public Athlete(String nom, String prenom, char sexe, int force, int agilite, int endurance, Pays pays, Equipe equipe, List<Epreuve> epreuves) throws GenderException {
        sexe = Character.toUpperCase(sexe);
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

    /**
     * Constructeur de la classe Athlete sans equipe et epreuves.
     *
     * @param nom Le nom de l'athlète.
     * @param prenom Le prénom de l'athlète.
     * @param sexe Le sexe de l'athlète ('M' pour masculin, 'F' pour féminin).
     * @param force La force de l'athlète.
     * @param agilite L'agilité de l'athlète.
     * @param endurance L'endurance de l'athlète.
     * @param pays Le pays de l'athlète.
     * @throws GenderException Si le sexe n'est pas 'M' ou 'F'.
     */
    public Athlete(String nom, String prenom, char sexe, int force, int agilite, int endurance, Pays pays) throws GenderException {
        this(nom, prenom, sexe, force, agilite, endurance, pays, null, new ArrayList<>());
    }

    /**
     * Constructeur de la classe Athlete seulement avec nom, prenom, sexe, pays.
     *
     * @param nom Le nom de l'athlète.
     * @param prenom Le prénom de l'athlète.
     * @param sexe Le sexe de l'athlète ('M' pour masculin, 'F' pour féminin).
     * @param pays Le pays de l'athlète.
     * @throws GenderException Si le sexe n'est pas 'M' ou 'F'.
     */
    public Athlete(String nom, String prenom, char sexe, Pays pays) throws GenderException{
        this(nom, prenom, sexe, (int)(Math.random() * 20), (int)(Math.random() * 20), (int)(Math.random() * 20), pays);
    }

    // Les getters
    /**
     * @return Le nom de l'athlète.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Le prénom de l'athlète.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return Le sexe de l'athlète.
     */
    public char getSexe() {
        return sexe;
    }

    /**
     * @return La force de l'athlète.
     */
    public int getForce() {
        return force;
    }

    /**
     * @return L'agilité de l'athlète.
     */
    public int getAgilite() {
        return agilite;
    }

    /**
     * @return L'endurance de l'athlète.
     */
    public int getEndurance() {
        return endurance;
    }

    /**
     * @return Le pays de l'athlète.
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * @return La liste des épreuves auxquelles l'athlète participe.
     */
    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    /**
     * Ajoute une épreuve à la liste des épreuves de l'athlète.
     *
     * @param e L'épreuve à ajouter.
     */
    public void ajouteEpreuve(Epreuve e) {
        this.epreuves.add(e);
    }

    /**
     * Retire une épreuve de la liste des épreuves de l'athlète.
     *
     * @param e L'épreuve à retirer.
     */
    public void retireEpreuve(Epreuve e) {
        if (!this.epreuves.contains(e)) this.epreuves.remove(e);
    }

    /**
     * @return L'équipe de l'athlète.
     */
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
