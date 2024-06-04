package fr.aftek;

import java.util.Random;

public class Sport {
    private static final double COEF_SANCTION_COMPETENCE_INFERIEUR_MIN = 2;
    private static final double COEF_SANCTION_COMPETENCE_INFERIEUR_MAX = 7;
    private static final int RANDOM_MAX = 70;
    private static final int RANDOM_MIN = 10;
    protected NomSport nomSport;
    protected float force;
    protected float agilite;
    protected float endurance;

    public Sport(NomSport nomSport, float force, float agilite, float endurance) {
        this.nomSport = nomSport;
        
        this.force = force;
        this.agilite = agilite;
        this.endurance = endurance;
    }
    
    public NomSport getNomSport() { return nomSport; }
    public float getForce() { return force; }
    public float getAgilite() { return agilite; }
    public float getEndurance() { return endurance; }

    public void setForce(int force) { this.force = force; }
    public void setAgilite(int agilite) { this.agilite = agilite; }
    public void setEndurance(int endurance) { this.endurance = endurance; }

    public int calculePoint(Athlete athlete) {
        int points = 100;
        Random random = new Random();
        if(athlete.getForce() < this.getForce()) points -= (this.getForce() - athlete.getForce()) * random.nextDouble(COEF_SANCTION_COMPETENCE_INFERIEUR_MIN, COEF_SANCTION_COMPETENCE_INFERIEUR_MAX);
        if(athlete.getAgilite() < this.getAgilite()) points -= (this.getAgilite() - athlete.getAgilite()) * random.nextDouble(COEF_SANCTION_COMPETENCE_INFERIEUR_MIN, COEF_SANCTION_COMPETENCE_INFERIEUR_MAX);
        if(athlete.getEndurance() < this.getEndurance()) points -= (this.getEndurance() - athlete.getEndurance()) * random.nextDouble(COEF_SANCTION_COMPETENCE_INFERIEUR_MIN, COEF_SANCTION_COMPETENCE_INFERIEUR_MAX);
        points -= random.nextInt(RANDOM_MIN, RANDOM_MAX);
        return points;
    }

    @Override
    public String toString() { return nomSport.toString(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Sport other = (Sport) obj;
        return nomSport == other.nomSport;
    }

    @Override
    public int hashCode() { return nomSport.hashCode(); }
    
}
