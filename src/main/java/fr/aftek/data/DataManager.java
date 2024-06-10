package fr.aftek.data;


import java.util.HashSet;
import java.util.Set;

import fr.aftek.*;

public class DataManager {
    public final Set<Pays> pays;
    public final Set<Athlete> athletes;
    public final Set<Equipe> equipes;
    public final Set<Sport> sports;
    public final Set<SportCollectif> sportsCollectifs;

    public DataManager() {
        pays = new HashSet<Pays>();
        athletes = new HashSet<Athlete>();
        equipes = new HashSet<Equipe>();
        sports = new HashSet<Sport>();
        sportsCollectifs = new HashSet<SportCollectif>();
    }

    public Set<Pays> getPays() {
        return pays;
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public Set<Equipe> getEquipes() {
        return equipes;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public Set<SportCollectif> getSportsCollectifs() {
        return sportsCollectifs;
    }

    public void addPays(Pays pays) {
        this.pays.add(pays);
    }

    public void addAthlete(Athlete athlete) {
        this.athletes.add(athlete);
    }

    public void addEquipe(Equipe equipe) {
        this.equipes.add(equipe);
    }

    public void addSport(Sport sport) {
        this.sports.add(sport);
    }

    public void addSportCollectif(SportCollectif sportCollectif) {
        this.sportsCollectifs.add(sportCollectif);
    }

    public void removePays(Pays pays) {
        this.pays.remove(pays);
    }

    public void removeAthlete(Athlete athlete) {
        this.athletes.remove(athlete);
    }

    public void removeEquipe(Equipe equipe) {
        this.equipes.remove(equipe);
    }

    public void removeSport(Sport sport) {
        this.sports.remove(sport);
    }

    public void removeSportCollectif(SportCollectif sportCollectif) {
        this.sportsCollectifs.remove(sportCollectif);
    }

    public Pays getPays(String nom) {
        for (Pays pays : this.pays) {
            if (pays.getNom().equals(nom)) {
                return pays;
            }
        }
        return null;
    }

    public Athlete getAthlete(String nom, String prenom, String pays){
        for (Athlete athlete : this.athletes) {
            if (athlete.getNom().equals(nom) && athlete.getPrenom().equals(prenom) && athlete.getPays().getNom().equals(pays)) {
                return athlete;
            }
        }
        return null;
    }

    public Athlete getAthlete(String nom, String prenom, Pays pays){
        return this.getAthlete(nom, prenom, pays.getNom());
    }
}
