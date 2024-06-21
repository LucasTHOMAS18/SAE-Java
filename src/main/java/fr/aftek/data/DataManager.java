package fr.aftek.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.aftek.Athlete;
import fr.aftek.Epreuve;
import fr.aftek.Equipe;
import fr.aftek.GenderException;
import fr.aftek.NomSport;
import fr.aftek.Pays;
import fr.aftek.Sport;
import fr.aftek.SportCollectif;
import fr.aftek.SportException;

/**
 * Classe DataManager, gère les données des pays, athlètes, équipes, épreuves, sports et sports collectifs
 */
public class DataManager {
    public final Set<Pays> pays;
    public final Set<Athlete> athletes;
    public final Set<Equipe> equipes;
    public final Set<Epreuve> epreuves;
    public final Set<Sport> sports;
    public final Set<SportCollectif> sportsCollectifs;

    /**
     * Constructeur de la classe DataManager, initialise les listes de pays, athlètes, équipes, épreuves, sports et sports collectifs
     */
    public DataManager() {
        pays = new HashSet<Pays>();
        athletes = new HashSet<Athlete>();
        equipes = new HashSet<Equipe>();
        epreuves = new HashSet<Epreuve>();
        sports = new HashSet<Sport>();
        sportsCollectifs = new HashSet<SportCollectif>();
    }
    
    /**
     * Récupère la liste des pays
     * @return la liste des pays
     */
    public Set<Pays> getPays() {
        return pays;
    }

    /**
     * Récupère la liste des athlètes
     * @return la liste des athlètes
     */
    public Set<Athlete> getAthletes() {
        return athletes;
    }

    /**
     * Récupère la liste des équipes
     * @return la liste des équipes
     */
    public Set<Equipe> getEquipes() {
        return equipes;
    }

    /**
     * Récupère la liste des épreuves
     * @return la liste des épreuves
     */
    public Set<Epreuve> getEpreuves() {
        return epreuves;
    }

    /**
     * Récupère la liste des sports
     * @return la liste des sports
     */
    public Set<Sport> getSports() {
        return sports;
    }

    /**
     * Récupère la liste des sports collectifs
     * @return la liste des sports collectifs
     */
    public Set<SportCollectif> getSportsCollectifs() {
        return sportsCollectifs;
    }

    /**
     * Ajoute un pays à la liste des pays
     * @param pays le pays à ajouter
     * @return le pays ajouté
     */
    public Pays addPays(Pays pays) {
        this.pays.add(pays);
        return pays;
    }

    /**
     * Ajoute une epreuve à la liste des epreuves
     * @param epreuve l'epreuve à ajouter
     * @return l'epreuve ajoutée
     */
    public Epreuve addEpreuve(Epreuve epreuve) {
        this.epreuves.add(epreuve);
        return epreuve;
    }

    public List<Epreuve> getEpreuvesByPays(String nomPays) {
        List<Epreuve> epreuvesPays = new ArrayList<>();
        Pays pays = getPays(nomPays);

        if (pays == null) {
            return epreuvesPays; // Le pays n'existe pas dans la liste
        }

        for (Epreuve epreuve : epreuves) {
            for (Athlete athlete : epreuve.getParticipants()) {
                if (athlete.getPays().equals(pays)) {
                    epreuvesPays.add(epreuve);
                    break;
                }
            }

            // Vérifier les équipes
            if (epreuve.getSport().getNomSport().estEquipe()) {
                for (Equipe equipe : ((SportCollectif) epreuve.getSport()).getEquipes()) {
                    if (equipe.getPays().equals(pays)) {
                        epreuvesPays.add(epreuve);
                        break;
                    }
                }
            }
        }

        return epreuvesPays;
    }


    /**
     * Ajoute un athlète à la liste des athlètes
     * @param athlete l'athlète à ajouter
     * @return l'athlète ajouté
     */
    public Athlete addAthlete(Athlete athlete) {
        this.athletes.add(athlete);
        return athlete;
    }

    /**
     * Ajoute une équipe à la liste des équipes
     * @param equipe l'équipe à ajouter
     * @return l'équipe ajoutée
     */
    public Equipe addEquipe(Equipe equipe) {
        this.equipes.add(equipe);
        return equipe;
    }

    /**
     * Ajoute un sport à la liste des sports
     * @param sport le sport à ajouter  
     * @return le sport ajouté
     */
    public Sport addSport(Sport sport) {
        this.sports.add(sport);
        return sport;
    }

    /**
     * Ajoute un sport collectif à la liste des sports collectifs
     * @param sportCollectif le sport collectif à ajouter
     * @return le sport collectif ajouté
     */
    public SportCollectif addSportCollectif(SportCollectif sportCollectif) {
        this.sportsCollectifs.add(sportCollectif);
        return sportCollectif;
    }

    /**
     * Crée une nouvelle épreuve avec le nom spécifié, une liste d'athlètes et un sport.
     * Tous les athlètes doivent être du même sexe, sinon une GenderException sera levée.
     *
     * @param nom le nom de l'épreuve
     * @param athletes la liste des athlètes participant à l'épreuve
     * @param sport le sport associé à l'épreuve
     * @return l'épreuve créée
     * @throws GenderException si les athlètes n'ont pas le même sexe
     */
    public Epreuve createEpreuve(String nom, List<Athlete> athletes, Sport sport) {
        char sexe = athletes.get(0).getSexe();
        for(Athlete a : athletes) {
            if(a.getSexe() != sexe) throw new GenderException("Les athletes n'ont pas le même genre");
            if(!a.getSport().equals(sport)) throw new SportException("Les athletes ne sont pas du même sport");;
        }
        Epreuve e = new Epreuve(nom, sexe, sport);
        for(Athlete a : athletes) {
            a.ajouteEpreuve(e);
            e.ajouteAthlete(a);
        }
        epreuves.add(e);
        return e;
    }

    public Epreuve createEpreuve(String nom, char sexe, Sport sport){
        Epreuve e = new Epreuve(nom, sexe, sport);
        epreuves.add(e);
        return e;
    }

    /**
     * Crée un nouvelle épreuve à partir d'une liste d'athletes et un sport
     * @param athletes la liste des athlètes participant à l'épreuve
     * @param sport le sport associé à l'épreuve
     * @return l'épreuve créée
     */
    public Epreuve createEpreuve(List<Athlete> athletes, Sport sport){
        return createEpreuve(sport.getNomSport().getNom(), athletes, sport);
    }

    /**
     * Crée un nouvelle épreuve avec un seul athlète et un sport
     * @param athlete l'athlète participant à l'épreuve
     * @param sport le sport associé à l'épreuve
     * @return l'épreuve créée
     */
    public Epreuve createEpreuve(Athlete athlete, Sport sport){
        return createEpreuve(Arrays.asList(athlete), sport);
    }

    /**
     * Supprime un pays de la liste des pays
     * @param pays le pays à supprimer
     */
    public void removePays(Pays pays) {
        this.pays.remove(pays);
    }

    /**
     * Supprime un athlète de la liste des athlètes
     * @param athlete l'athlète à supprimer
     */
    public void removeAthlete(Athlete athlete) {
        this.athletes.remove(athlete);
    }

    /**
     * Supprime une équipe de la liste des équipes
     * @param equipe l'équipe à supprimer
     */
    public void removeEquipe(Equipe equipe) {
        this.equipes.remove(equipe);
    }

    /**
     * Supprime un sport de la liste des sports
     * @param sport le sport à supprimer
     */
    public void removeSport(Sport sport) {
        this.sports.remove(sport);
    }

    /**
     * Supprime un sport collectif de la liste des sports collectifs
     * @param sportCollectif le sport collectif à supprimer
     */
    public void removeSportCollectif(SportCollectif sportCollectif) {
        this.sportsCollectifs.remove(sportCollectif);
    }

    /**
     * Récupère un pays à partir de son nom
     * @param nom le nom du pays
     * @return le pays correspondant au nom, null si le pays n'existe pas
     */
    public Pays getPays(String nom) {
        for (Pays pays : this.pays) {
            if (pays.getNom().equals(nom)) {
                return pays;
            }
        }
        return null;
    }

    /**
     * Récupère un athlète à partir de son nom, prénom et pays
     * @param nom le nom de l'athlète
     * @param prenom le prénom de l'athlète
     * @param nomPays le nom du pays de l'athlète
     * @return l'athlète correspondant au nom, prénom et pays, null si l'athlète n'existe pas
     */
    public Athlete getAthlete(String nom, String prenom, String nomPays){
        for (Athlete athlete : this.athletes) {
            if (athlete.getNom().equals(nom) && athlete.getPrenom().equals(prenom) && athlete.getPays().getNom().equals(nomPays)) {
                return athlete;
            }
        }
        return null;
    }

    /**
     * Récupère un athlète à partir de son nom, prénom et pays
     * @param nom le nom de l'athlète
     * @param prenom le prénom de l'athlète
     * @param pays le pays de l'athlète
     * @return l'athlète correspondant au nom, prénom et pays, null si l'athlète n'existe pas
     */
    public Athlete getAthlete(String nom, String prenom, Pays pays){
        return this.getAthlete(nom, prenom, pays.getNom());
    }

    /**
     * Récupère le premier athlète à partir de son nom, prénom, si plusieurs athlètes ont le même nom et prénom il renvoie le premier trouvé
     * @param nom le nom de l'athlète
     * @param prenom le prénom de l'athlète
     * @return l'athlète correspondant au nom, prénom et pays, null si l'athlète n'existe pas
     */
    public Athlete getAthlete(String nom, String prenom){
        for (Athlete athlete : this.athletes) {
            if (athlete.getNom().equals(nom) && athlete.getPrenom().equals(prenom)) {
                return athlete;
            }
        }
        return null;
    }    

    public Equipe getEquipe(String nom, String pays){
        for (Equipe equipe : this.equipes) {
            if (equipe.getNom().equals(nom) && equipe.getPays().getNom().equals(pays)) {
                return equipe;
            }
        }
        return null;
    }

    /**
     * Récupère un sport à partir de son nom
     * @param sport le nom du sport
     * @return le sport correspondant au nom, null si le sport n'existe pas
     */
    public Sport getSport(String nomSport){
        Set<String> sportsS = new HashSet<>();
        for(NomSport nom : NomSport.values()) sportsS.add(nom.getNom());
        for (Sport s : this.sports) {
            if (s.getNomSport().getNom().equals(nomSport)) {
                return s;
            }
        }
        return null;
    }

    public Sport getSport(NomSport nomSport){
        for (Sport s : this.sports) {
            if (s.getNomSport().equals(nomSport)) {
                return s;
            }
        }
        return null;
    }

    public Epreuve getEpreuve(String nom, String sport){
        for (Epreuve e : this.epreuves) {
            if (e.getNom().equals(nom) && e.getSport().getNomSport().getNom().equals(sport)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Récupère les médailles d'or, d'argent et de bronze pour un athlète donnée en paramètre
     * @param nom le nom d'un athlete
     * @param prenom le prénom d'un athlete
     * @return Les médailles d'or, d'argent et de bronze pour un athlète sous la forme d'une array de int
     */
    public int[] getNbMedailles(String nom, String prenom) {
        Athlete athlete = getAthlete(nom, prenom);
        if (athlete == null) {
            return new int[]{0, 0, 0};
        }

        int nbOr = 0;
        int nbArgent = 0;
        int nbBronze = 0;

        for (Epreuve epreuve : epreuves) {
            Map<Athlete, Integer> classement = epreuve.getClassement();
            if (classement.containsKey(athlete)) {
                int position = 1;
                for (Map.Entry<Athlete, Integer> entry : classement.entrySet()) {
                    if (entry.getKey().equals(athlete)) {
                        if (position == 1) {
                            nbOr++;
                        } else if (position == 2) {
                            nbArgent++;
                        } else if (position == 3) {
                            nbBronze++;
                        }
                        break;
                    }
                    position++;
                }
            }
        }
        return new int[]{nbOr, nbArgent, nbBronze};
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