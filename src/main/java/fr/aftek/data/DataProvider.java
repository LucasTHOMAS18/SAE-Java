package fr.aftek.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import fr.aftek.Athlete;
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
        Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource(filename).getFile()));
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
            Athlete a = new Athlete(nom, prenom, sexe,force, agilite, endurance, pays, sport); // TODO ajouter sport
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
        ResultSet pays = st.executeQuery("SELECT * FROM Pays");

        while (pays.next()) {
            manager.addPays(new Pays(pays.getString(1)));
        }

        ResultSet athletes = st.executeQuery("SELECT * FROM Athlete");

        while (athletes.next()) {
            manager.addAthlete(new Athlete(athletes.getString(2), athletes.getString(3), athletes.getString(4).charAt(0), athletes.getInt(5), athletes.getInt(6), athletes.getInt(7), manager.getPays(athletes.getString(8)), null)); // TODO ajouter sport
        }

        ResultSet sports = st.executeQuery("SELECT * FROM Sport WHERE individuel=true");

        while (sports.next()) {
            manager.addSport(new Sport(NomSport.getNomSport(sports.getString(2)), sports.getInt(3), sports.getInt(4), sports.getInt(5)));
        }

        ResultSet sportsCollectifs = st.executeQuery("SELECT * FROM Sport WHERE individuel=false");

        while(sportsCollectifs.next()) {
            manager.addSportCollectif(new SportCollectif(NomSport.getNomSport(sports.getString(2)), sports.getInt(3), sports.getInt(4), sports.getInt(5), 0, 0));
        }

        ResultSet athleteSport = st.executeQuery("SELECT nomAthlete,prenomAthlete,nomSport FROM Athlete natural left join Sport");

        while(athleteSport.next()){
            manager.getAthlete(athletes.getString(1), athletes.getString(2)).setSport(manager.getSport(athleteSport.getString(3)));
        }
    }

    public void saveSQL(ConnexionMySQL connexion) throws SQLException{
        
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
