package fr.aftek.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.aftek.*;

public class DataManager {
    public final Set<Pays> pays;
    public final Set<Athlete> athletes;
    public final Set<Equipe> equipes;
    public final Set<Epreuve> epreuves;
    public final Set<Sport> sports;
    public final Set<SportCollectif> sportsCollectifs;

    public DataManager() {
        pays = new HashSet<Pays>();
        athletes = new HashSet<Athlete>();
        equipes = new HashSet<Equipe>();
        epreuves = new HashSet<Epreuve>();
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

    public Set<Epreuve> getEpreuves() {
        return epreuves;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public Set<SportCollectif> getSportsCollectifs() {
        return sportsCollectifs;
    }

    public Pays addPays(Pays pays) {
        this.pays.add(pays);
        return pays;
    }

    public Athlete addAthlete(Athlete athlete) {
        this.athletes.add(athlete);
        return athlete;
    }

    public Equipe addEquipe(Equipe equipe) {
        this.equipes.add(equipe);
        return equipe;
    }

    public Sport addSport(Sport sport) {
        this.sports.add(sport);
        return sport;
    }

    public SportCollectif addSportCollectif(SportCollectif sportCollectif) {
        this.sportsCollectifs.add(sportCollectif);
        return sportCollectif;
    }

    public Epreuve createEpreuve(String nom, List<Athlete> athletes, Sport sport){
        char sexe = athletes.get(0).getSexe();
        for(Athlete a : athletes){if(a.getSexe()!=sexe)throw new GenderException("Les athletes n'ont pas le même genre");}
        Epreuve e = new Epreuve(nom, sexe, sport);
        for(Athlete a : athletes){a.ajouteEpreuve(e);e.ajouteAthlete(a);}
        epreuves.add(e);
        return e;
    }

    public Epreuve createEpreuve(List<Athlete> athletes, Sport sport){
        return createEpreuve(sport.getNomSport().getNom(), athletes, sport);
    }

    public Epreuve createEpreuve(Athlete athlete, Sport sport){
        return createEpreuve(Arrays.asList(athlete), sport);
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

    public Sport getSport(String sport){
        Set<String> sportsS = new HashSet<>();
        for(NomSport nom : NomSport.values()) sportsS.add(nom.getNom());
        for (Sport s : this.sports) {
            if (s.getNomSport().getNom().equals(sport)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(!(obj instanceof DataManager)) return false;
        DataManager other = (DataManager) obj;
        return this.pays.equals(other.pays) 
        && this.athletes.equals(other.athletes) 
        && this.equipes.equals(other.equipes) 
        && this.epreuves.equals(other.epreuves) 
        && this.sports.equals(other.sports) 
        && this.sportsCollectifs.equals(other.sportsCollectifs);
    }
}
