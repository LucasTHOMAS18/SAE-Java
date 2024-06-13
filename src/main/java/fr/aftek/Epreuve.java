package fr.aftek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Epreuve {
    private String nom;
    private Map<Athlete, Integer> classement;
    private List<Athlete> participants;
    private Sport sport;
    private char sexe;

    /**
     * Constructeur pour initialiser une épreuve avec un nom, un sexe et un sport.
     *
     * @param nom Le nom de l'épreuve.
     * @param sexe Le sexe des participants ('M' pour masculin, 'F' pour féminin).
     * @param sport Le sport de l'épreuve.
     */
    public Epreuve(String nom, char sexe, Sport sport) {
        this.nom = nom;
        this.participants = new ArrayList<>();
        this.classement = new HashMap<>();
        this.sport = sport;
        this.sexe = sexe;
    }

    /**
     * Simule l'épreuve en calculant les points pour chaque athlète participant et retourne le classement.
     *
     * @return Le classement des athlètes avec leurs points.
     */
    public Map<Athlete, Integer> simuleEpreuve() {
        Random random = new Random();
        for (Athlete athlete : participants) {
            int points = this.sport.calculePoint(athlete);
            for (Integer pts : classement.values()) {
                if(pts == points) points += (random.nextInt(0, 1) == 1) ? 1 : -1;
            }
            this.classement.put(athlete, points);
        }
        return classement;
    }

    /**
     * Ajoute un athlète à la liste des participants de l'épreuve.
     *
     * @param athlete L'athlète à ajouter.
     */
    public void ajouteAthlete(Athlete athlete) {
        this.participants.add(athlete);
    }

    public void retireAthlete(Athlete athlete){
        this.participants.remove(athlete);
    }

    /**
     * Retire un athlète de la liste des participants de l'épreuve.
     *
     * @param nom Le de nom de l'athlète à retirer.
     * @param prenom Le de prenom de l'athlète à retirer.
     */
    public void retireAthlete(String nom, String prenom){
        for (Athlete athlete : participants) {
            if(athlete.getNom().equals(nom) && athlete.getPrenom().equals(prenom)){
                this.participants.remove(athlete);
                return;
            }
        }
    }

    /**
     * Fusionne cette épreuve avec une autre épreuve.
     *
     * @param e L'épreuve à fusionner.
     * @return L'épreuve fusionnée.
     * @throws GenderException Si les épreuves ne sont pas pour le même sexe.
     * @throws RuntimeException Si les épreuves ne sont pas pour le même sport.
     */
    public Epreuve fusionEpreuve(Epreuve e){
        if(this.getSexe()!=e.getSexe()) throw new GenderException("Pas le même genre");
        if(!this.getSport().equals(e.getSport())) throw new RuntimeException("Pas le même sport");
        for (Athlete athlete : e.getParticipants()) {
            this.participants.add(athlete);
            athlete.retireEpreuve(e);
            athlete.ajouteEpreuve(this);
        }
        this.classement.putAll(e.getClassement());
        return this;
    }
    
// Getters

    /**
     * @return Le classement des athlètes avec leurs points.
     */
    public Map<Athlete, Integer> getClassement() {
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
    public List<Athlete> getParticipants() {
        return participants;
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
        return Objects.hash(nom, classement, participants, sport, sexe);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Epreuve de " + this.sport + (this.sexe == 'F' ? " féminin" : " masculin") + " nommée " + this.nom + " dont les participants sont: " + this.participants.toString());
        if(this.classement.size() == 0){
            sb.append("\nAucun classement n'a encore été calculé");
        }else{
            sb.append("\nClassement: " + this.classement.toString());
        }
        return sb.toString();
    }
}
