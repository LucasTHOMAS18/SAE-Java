package fr.aftek.data;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DataProvider {
    private DataManager manager;

    public DataProvider(DataManager manager) {
        this.manager = manager;
    }

    public DataProvider() {
        manager = new DataManager();
    }

    public void loadCSV(String filename){
        try{
            Scanner scanner = new Scanner(new File(filename));
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String[] data = scanner.nextLine().split(",");
                String nom = data[0];
                String prenom = data[1];
                char sexe = data[2].charAt(0);
                String paysS = data[3];
                String sportS = data[4];
                float force = Integer.parseInt(data[5]);
                float endurance = Integer.parseInt(data[6]);
                float agilite = Integer.parseInt(data[7]);
                Pays pays = this.manager.getPays(paysS);
                Sport sport = this.manager.getSport(sportS);
                if(sport == null){ 
                    float[] stats = NomSport.getStats(sportS);
                    if(stats == null) continue;
                    sport = this.manager.addSport(new Sport(NomSport.getNomSport(sportS), force, agilite, endurance));
                }
                if(pays == null) pays = this.manager.addPays(new Pays(paysS));
                this.manager.addAthlete(new Athlete(nom, prenom, sexe,force, agilite, endurance, pays));
            }
            scanner.close();
        }catch(Exception e){

        }
    }

    public void loadSQL(ConnexionMySQL connexion) throws SQLException {
        Statement st = connexion.createStatement();
        ResultSet pays = st.executeQuery("SELECT * FROM PAYS");

        while (pays.next()) {
            manager.addPays(new Pays(pays.getString(1)));
        }

        
    }

    public DataManager getManager() {
        return manager;
    }
}
