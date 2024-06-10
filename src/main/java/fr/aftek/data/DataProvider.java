package fr.aftek.data;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import fr.aftek.Pays;

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
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch(Exception e){

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
