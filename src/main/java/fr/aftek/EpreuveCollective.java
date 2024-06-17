package fr.aftek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class EpreuveCollective {
    private String nom;
    private Map<Equipe, Integer> classement;
    private List<Equipe> equipes;
    private Sport sport;
    private char sexe;

    /**
     * Constructeur pour initialiser une épreuve avec un nom, un sexe et un sport.
     *
     * @param nom Le nom de l'épreuve.
     * @param sexe Le sexe des participants ('M' pour masculin, 'F' pour féminin).
     * @param sport Le sport de l'épreuve.
     */
    public EpreuveCollective(String nom, char sexe, Sport sport) {
        this.nom = nom;
        this.equipes = new ArrayList<>();
        this.classement = new HashMap<>();
        this.sport = sport;
        this.sexe = sexe;
    }

    /**
     * Simule l'épreuve en calculant les points pour chaque athlète participant et retourne le classement.
     *
     * @return Le classement des athlètes avec leurs points.
     */
    public Map<Equipe, Integer> simuleEpreuve() {
        Random random = new Random();
        for (Equipe equipe : equipes) {
            int points = 0;
            for (Athlete athlete : equipe.getAthletes()) {
                points += this.sport.calculePoint(athlete);
            }

            for (Integer pts : classement.values()) {
                if(pts == points) points += (random.nextInt(0, 1) == 1) ? 1 : -1;
            }

            this.classement.put(equipe, (Integer) points / equipe.getAthletes().size());
        }

        return classement;
    }

    /**
     * Ajoute une équipe à la liste des participants de l'épreuve.
     *
     * @param equipe L'équipe à ajouter.
     */
    public void ajouteEquipe(Equipe equipe) {
        this.equipes.add(equipe);
    }

    /**
     * Supprime une équipe de la liste des participants de l'épreuve.
     * @param equipe L'équipe à retirer.
     */
    public void retireEquipe(Equipe equipe) {
        this.equipes.remove(equipe);
    }

    /**
     * Retire une équipe de la liste des participants de l'épreuve avec son nom.
     * @param nom Le nom de l'équipe à retirer.
     */
    public void retireEquipe(String nom) {
        for (Equipe equipe : equipes) {
            if (equipe.getNom().equals(nom)) {
                equipes.remove(equipe);
                return;
            }
        }
    }

    // Getters


    /**
     * @return Le classement des équipes avec leurs points.
     */
    public Map<Equipe, Integer> getClassement() {
        return classement;
    }

    /**
     * @return Le nom de l'épreuve.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return La liste des participants à l'épreuve.
     */
    public List<Equipe> getEquipes() {
        return equipes;
    }

    /**
     * @return Le sexe des participants ('M' pour masculin, 'F' pour féminin).
     */
    public char getSexe() {
        return sexe;
    }

    /**
     * @return Le sport de l'épreuve.
     */
    public Sport getSport() {
        return sport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, classement, equipes, sport, sexe);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Epreuve de " + this.sport + (this.sexe == 'F' ? " féminin" : " masculin") + " nommée " + this.nom + " dont les équipes sont: " + this.equipes.toString());
        if(this.classement.size() == 0){
            sb.append("\nAucun classement n'a encore été calculé");
        }else{
            sb.append("\nClassement: " + this.classement.toString());
        }
        return sb.toString();
    }
}
