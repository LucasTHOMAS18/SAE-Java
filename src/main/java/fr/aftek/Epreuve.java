package fr.aftek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Epreuve {
    private String nom;
    private Map<Athlete, Integer> classement;
    private List<Athlete> participants;
    private Sport sport;
    private char sexe;

    public Epreuve(String nom, char sexe, Sport sport) {
        this.nom = nom;
        this.participants = new ArrayList<>();
        this.classement = new HashMap<>();
        this.sport = sport;
        this.sexe = sexe;
    }

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

    public void ajouteAthlete(Athlete athlete) {
        this.participants.add(athlete);
    }

    public void retireAthlete(Athlete athlete){
        this.participants.remove(athlete);
    }

    public void retireAthlete(String nom, String prenom){
        for (Athlete athlete : participants) {
            if(athlete.getNom().equals(nom) && athlete.getPrenom().equals(prenom)){
                this.participants.remove(athlete);
                return;
            }
        }
    }
    
    public Map<Athlete, Integer> getClassement() {
        return classement;
    }

    public String getNom() {
        return nom;
    }

    public List<Athlete> getParticipants() {
        return participants;
    }

    public char getSexe() {
        return sexe;
    }

    public Sport getSport() {
        return sport;
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
