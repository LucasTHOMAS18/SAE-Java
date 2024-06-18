package fr.aftek.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fr.aftek.Athlete;
import fr.aftek.Epreuve;
import fr.aftek.EpreuveCollective;
import fr.aftek.Equipe;
import fr.aftek.NomSport;
import fr.aftek.Pays;
import fr.aftek.Sport;
import fr.aftek.SportCollectif;

/**
 * Classe DataProvider, permet de charger et sauvegarder les données des athlètes, pays et sports à partir d'une BD ou d'un fichier CSV
 */
public class DataProvider {
    private DataManager manager;

    /**
     * Constructeur de la classe DataProvider
     * @param manager le gestionnaire de données
     */
    public DataProvider(DataManager manager) {
        this.manager = manager;
    }

    /**
     * Constructeur de la classe DataProvider
     */
    public DataProvider() {
        manager = new DataManager();
    }

    /**
     * Charge les données des athlètes, pays et sports à partir d'un fichier CSV
     * @param filename le nom du fichier CSV
     * @throws FileNotFoundException
     */
    public void loadCSV(String filename) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            String nom = data[0];
            String prenom = data[1];
            char sexe = data[2].charAt(0);
            String paysS = data[3];
            String sportS = data[4];
            int force = Integer.parseInt(data[5]);
            int endurance = Integer.parseInt(data[6]);
            int agilite = Integer.parseInt(data[7]);
            Pays pays = this.manager.getPays(paysS);
            Sport sport = this.manager.getSport(sportS);
            if(sport == null){ 
                float[] stats = NomSport.getStats(sportS);
                if(stats == null) continue;
                sport = this.manager.addSport(new Sport(NomSport.getNomSport(sportS), force, agilite, endurance));
            }
            if(pays == null) pays = this.manager.addPays(new Pays(paysS));
            Athlete a = new Athlete(nom, prenom, sexe,force, agilite, endurance, pays, sport);
            this.manager.addAthlete(a);
            this.manager.createEpreuve(a, sport);
        }
        scanner.close();
    }

    /**
     * Sauvegarde les données des athlètes, pays et sports dans un fichier CSV
     * @param filename le nom du fichier CSV
     * @throws FileNotFoundException
     */
    public void saveCSV(String filename) throws FileNotFoundException{
        File f = new File(filename);
        PrintWriter pw = new PrintWriter(f);
        pw.println("Nom,Prénom,Sexe,Pays,Épreuve,Force,Endurance,Agilite");
        for(Athlete a : manager.getAthletes()){
            String sport = "NULL";
            if(!a.getEpreuves().isEmpty()) sport = a.getEpreuves().get(0).getSport().getNomSport().getNom();
            pw.println(a.getNom()+","+a.getPrenom()+","+a.getSexe()+","+a.getPays().getNom()+","+sport+","+a.getForce()+","+a.getEndurance()+","+a.getAgilite());
        }
        pw.close();
    }

    /**
     * Charge les données des athlètes, pays et sports à partir d'une base de données MySQL
     * @param connexion la connexion à la base de données
     * @throws SQLException
     */
    public void loadSQL(ConnexionMySQL connexion) throws SQLException {
        Statement st = connexion.createStatement();
        
        // Creation des pays
        ResultSet pays = st.executeQuery("SELECT * FROM Pays");
        while (pays.next()) {
            manager.addPays(new Pays(pays.getString(1)));
        }

        // Creation des equipes
        ResultSet equipes = st.executeQuery("SELECT * FROM Equipe");
        Map<Integer, Equipe> equipesMap = new HashMap<>(); 
        while (equipes.next()) {
            Equipe equipe = new Equipe(equipes.getString(2), equipes.getString(3));
            equipesMap.put(equipes.getInt(1), equipe);
            manager.addEquipe(equipe);
        }

        // Creation des sports et sport collectifs
        ResultSet sports = st.executeQuery("SELECT * FROM Sport");
        while (sports.next()) {
            if (sports.getBoolean(5)) {
                manager.addSport(new Sport(NomSport.getNomSport(sports.getString(1)), sports.getInt(2), sports.getInt(3), sports.getInt(4)));
            } else {
                manager.addSport(new SportCollectif(NomSport.getNomSport(sports.getString(1)), sports.getInt(2), sports.getInt(3), sports.getInt(4), sports.getInt(5), sports.getInt(6)));
            }
        }

        // Creation des athletes
        ResultSet athletes = st.executeQuery("SELECT * FROM Athlete");
        Map<Integer, Athlete> athletesMap = new HashMap<>();
        while (athletes.next()) {
            Pays lePlays = manager.getPays(athletes.getString(8));
            Sport leSport = manager.getSport(athletes.getString(9));
            Athlete athlete = new Athlete(athletes.getString(2), athletes.getString(3), athletes.getString(4).charAt(0), athletes.getInt(5), athletes.getInt(6), athletes.getInt(7), lePlays, leSport);
            
            athletesMap.put(athletes.getInt(1), athlete);
            manager.addAthlete(athlete);
        
            // Ajouter l'ahtlete à l'équipe
            if(athletes.getInt(10)!= 0) equipesMap.get(athletes.getInt(10)).ajouteAthlete(athlete);
        }

        // Creation des epreuves
        ResultSet epreuves = st.executeQuery("SELECT * FROM Epreuve");
        Map<Integer, Epreuve> epreuvesMap = new HashMap<>();
        Map<Integer, EpreuveCollective> epreuvesCollectivesMap = new HashMap<>();
        while (epreuves.next()) {
            if (epreuves.getBoolean(5)) {
                Epreuve epreuve = new Epreuve(epreuves.getString(2), epreuves.getString(3).charAt(0), manager.getSport(epreuves.getString(4)));
                epreuvesMap.put(epreuves.getInt(1), epreuve);
                manager.addEpreuve(epreuve);
            } else {
                EpreuveCollective epreuve = new EpreuveCollective(epreuves.getString(2), epreuves.getString(3).charAt(0), manager.getSport(epreuves.getString(4)));
                epreuvesCollectivesMap.put(epreuves.getInt(1), epreuve);
                manager.addEpreuveCollective(epreuve);
            }
        }
        
        // Ajout des athletes aux epreuves
        ResultSet participeIndividuel = st.executeQuery("SELECT * FROM Participe");
        while (participeIndividuel.next()) {
            epreuvesMap.get(participeIndividuel.getInt(1)).ajouteAthlete(athletesMap.get(participeIndividuel.getInt(2)));
        }

        // Ajout des equipes aux epreuves
        ResultSet participeCollectif = st.executeQuery("SELECT * FROM ParticipeCollectif");
        while (participeCollectif.next()) {
            epreuvesCollectivesMap.get(participeCollectif.getInt(1)).ajouteEquipe(equipesMap.get(participeCollectif.getInt(2)));
        }
    }

    public void saveSQL(ConnexionMySQL connexion) throws SQLException{
        Statement st = connexion.createStatement();
        
        st.executeUpdate("DELETE FROM ParticipeCollectif");
        st.executeUpdate("DELETE FROM Participe");
        st.executeUpdate("DELETE FROM Epreuve");
        st.executeUpdate("DELETE FROM Athlete");
        st.executeUpdate("DELETE FROM Equipe");
        st.executeUpdate("DELETE FROM Sport");
        st.executeUpdate("DELETE FROM Pays");

        // Pays
        for (Pays pays : manager.getPays()) {
            st.executeUpdate("INSERT INTO Pays VALUES ('" + pays.getNom() + "')");
        }   

        // Sports
        for (Sport sport : manager.getSports()) {
            if (sport instanceof SportCollectif) {
                SportCollectif sportCollectif = (SportCollectif) sport;
                st.executeUpdate("INSERT INTO Sport VALUES ('" + sport.getNomSport().getNom() + "', " + sport.getForce() + ", " + sport.getEndurance() + ", " + sport.getAgilite() + ", true, " + sportCollectif.getNbEquipes() + ", " + sportCollectif.getNbJoueursParEquipe() + ")");
            } else {
                st.executeUpdate("INSERT INTO Sport VALUES ('" + sport.getNomSport().getNom() + "', " + sport.getForce() + ", " + sport.getEndurance() + ", " + sport.getAgilite() + ", 0, NULL, NULL)");
            }
        }

        // Equipes
        Map<Equipe, Integer> idEquipes = new HashMap<>();
        int id = 0;
        for (Equipe equipe : manager.getEquipes()) {
            idEquipes.put(equipe, ++id);
            st.executeUpdate("INSERT INTO Equipe VALUES (" + id + "'" + equipe.getNom() + "', '" + equipe.getPays().getNom() + "')");
        }

        // Athletes
        Map<Athlete, Integer> idAthletes = new HashMap<>();
        int idAthlete = 0;
        for (Athlete athlete : manager.getAthletes()) {
            idAthletes.put(athlete, ++idAthlete);
            st.executeUpdate("INSERT INTO Athlete VALUES (" + idAthlete + ",'" + athlete.getNom() + "', '" + athlete.getPrenom() + "', '" + athlete.getSexe() + "', " + athlete.getForce() + ", " + athlete.getAgilite() + ", " + athlete.getEndurance() + ", '" + athlete.getPays().getNom() + "', '" + athlete.getSport().getNomSport().getNom() + "', " + idEquipes.get(athlete.getEquipe()) + ")");
        }

        // Epreuves
        for (Epreuve epreuve : manager.getEpreuves()) {
            st.executeUpdate("INSERT INTO Epreuve VALUES (NULL, '" + epreuve.getNom() + "', '" + epreuve.getSexe() + "', '" + epreuve.getSport().getNomSport().getNom() + "', false)");
            for (Athlete athlete : epreuve.getParticipants()) {
                st.executeUpdate("INSERT INTO Participe VALUES (" + id + ", " + idAthletes.get(athlete) + ")");
            }
        }

        for (EpreuveCollective epreuveCollective : manager.getEpreuvesCollectives()) {
            st.executeUpdate("INSERT INTO Epreuve VALUES (NULL, '" + epreuveCollective.getNom() + "', '" + epreuveCollective.getSexe() + "', '" + epreuveCollective.getSport().getNomSport().getNom() + "', true)");
            for (Equipe equipe : epreuveCollective.getEquipes()) {
                st.executeUpdate("INSERT INTO ParticipeCollectif VALUES (" + id + ", " + idEquipes.get(equipe) + ")");
            }
        }
    }

    /**
     * Retourne le gestionnaire de données
     * @return le gestionnaire de données
     */
    public DataManager getManager() {
        return manager;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof DataProvider)) {
            return false;
        }
        DataProvider provider = (DataProvider) obj;
        return this.getManager().equals(provider.getManager());
    }
}
