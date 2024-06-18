package fr.aftek;

import java.util.Objects;
import java.util.Random;

/**
 * Classe Sport, représente un sport des JO
 */
public class Sport {
    private static final double COEF_SANCTION_COMPETENCE_INFERIEUR_MIN = 2;
    private static final double COEF_SANCTION_COMPETENCE_INFERIEUR_MAX = 7;
    private static final int RANDOM_MAX = 70;
    private static final int RANDOM_MIN = 10;
    protected NomSport nomSport;
    protected float force;
    protected float agilite;
    protected float endurance;

    /**
     * Constructeur de la classe Sport
     * @param nomSport le nom du sport
     * @param force la force requise pour le sport
     * @param agilite l'agilité requise pour le sport
     * @param endurance l'endurance requise pour le sport
     */
    public Sport(NomSport nomSport, float force, float agilite, float endurance) {
        this.nomSport = nomSport;
        
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
    }
    
    /**
     * Retourne le nom du sport
     * @return le nom du sport
     */
    public NomSport getNomSport() { return nomSport; }
    
    /**
     * Retourne la force requise pour le sport
     * @return la force requise pour le sport
     */
    public float getForce() { return force; }
    
    /**
     * Retourne l'agilité requise pour le sport
     * @return l'agilité requise pour le sport
     */
    public float getAgilite() { return agilite; }
    
    /** Retourne l'endurance requise pour le sport
     * @return l'endurance requise pour le sport
     */
    public float getEndurance() { return endurance; }


    /**
     * Modifie la force requise pour le sport
     * @param force la force requise pour le sport
     */
    public void setForce(int force) { this.force = force; }
    
    /**
     * Modifie l'agilité requise pour le sport
     * @param agilite l'agilité requise pour le sport
     */
    public void setAgilite(int agilite) { this.agilite = agilite; }
    
    /**
     * Modifie l'endurance requise pour le sport
     * @param endurance l'endurance requise pour le sport
     */
    public void setEndurance(int endurance) { this.endurance = endurance; }

    /**
     * Renvoie true si le sport est en équipe
     * @return est-ce que le sport est en équipe
     */
    public boolean enEquipe(){ return this.nomSport.estEquipe();}


    /**
     * Calcule les points d'un athlète pour ce sport
     * @param athlete l'athlète
     * @return les points de l'athlète pour ce sport
     */
    public int calculePoint(Athlete athlete) {
        return this.calculePoint(athlete.getForce(), athlete.getAgilite(), athlete.getEndurance());
    }


    /**
     * Calcule les points d'un athlète pour ce sport
     * @param force la force de l'athlète
     * @param agilite l'agilité de l'athlète
     * @param endurance l'endurance de l'athlète
     * @return les points de l'athlète pour ce sport
     */
    public int calculePoint(float force, float agilite, float endurance){
        int points = 100;
        Random random = new Random();
        if(force < this.getForce()) points -= (this.getForce() - force) * random.nextDouble(COEF_SANCTION_COMPETENCE_INFERIEUR_MIN, COEF_SANCTION_COMPETENCE_INFERIEUR_MAX);
        if(agilite < this.getAgilite()) points -= (this.getAgilite() - agilite) * random.nextDouble(COEF_SANCTION_COMPETENCE_INFERIEUR_MIN, COEF_SANCTION_COMPETENCE_INFERIEUR_MAX);
        if(endurance < this.getEndurance()) points -= (this.getEndurance() - endurance) * random.nextDouble(COEF_SANCTION_COMPETENCE_INFERIEUR_MIN, COEF_SANCTION_COMPETENCE_INFERIEUR_MAX);
        points -= random.nextInt(RANDOM_MIN, RANDOM_MAX);
        return points;
    }

    @Override
    public String toString() { return nomSport.getNom(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Sport other = (Sport) obj;
        return nomSport == other.nomSport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomSport,force,agilite,endurance);
    }
    
}
