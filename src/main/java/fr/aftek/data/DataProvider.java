package fr.aftek.data;

import java.io.File;
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
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch(Exception e){
            
        }
    }

    public void loadSQL(ConnexionMySQL connexion) {
        
    }

    public DataManager getManager() {
        return manager;
    }
}
