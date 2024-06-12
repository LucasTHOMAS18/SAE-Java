package fr.aftek.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import fr.aftek.*;

public class DataProvider {
    private DataManager manager;

    public DataProvider(DataManager manager) {
        this.manager = manager;
    }

    public DataProvider() {
        manager = new DataManager();
    }

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
            Athlete a = new Athlete(nom, prenom, sexe,force, agilite, endurance, pays);
            this.manager.addAthlete(a);
            this.manager.createEpreuve(a, sport);
        }
        scanner.close();
    }

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

    public void loadSQL(ConnexionMySQL connexion) throws SQLException {
        Statement st = connexion.createStatement();
        ResultSet pays = st.executeQuery("SELECT * FROM Pays");

        while (pays.next()) {
            manager.addPays(new Pays(pays.getString(1)));
        }

        ResultSet athletes = st.executeQuery("SELECT * FROM Athlete");

        while (athletes.next()) {
            manager.addAthlete(new Athlete(athletes.getString(2), athletes.getString(3), athletes.getString(4).charAt(0), athletes.getInt(5), athletes.getInt(6), athletes.getInt(7), manager.getPays(athletes.getString(8))));
        }

        ResultSet sports = st.executeQuery("SELECT * FROM Sport WHERE collectif=false");

        while (sports.next()) {
            manager.addSport(new Sport(NomSport.getNomSport(sports.getString(2)), sports.getInt(3), sports.getInt(4), sports.getInt(5)));
        }

        ResultSet sportsCollectifs = st.executeQuery("SELECT * FROM Sport WHERE collectif=true");

        while(sportsCollectifs.next()) {
            ResultSet athletesConcernes = st.executeQuery("SELECT nomAthlete, prenomAthlete, sexe,forces,agilite,endurance,nomPays,idEquipe FROM Athlete natural join ParticipeCollectif WHERE idEpreuve");
            manager.addSportCollectif(new SportCollectif(NomSport.getNomSport(sports.getString(2)), sports.getInt(3), sports.getInt(4), sports.getInt(5), 0, 0));
        }
    }

    public void saveSQL(ConnexionMySQL connexion) throws SQLException{

    }

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
