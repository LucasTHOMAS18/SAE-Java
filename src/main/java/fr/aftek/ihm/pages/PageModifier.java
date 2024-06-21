package fr.aftek.ihm.pages;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.Controleur;
import fr.aftek.ihm.controleurs.ControleurModifAthlete;
import fr.aftek.ihm.controleurs.ControleurModifEpreuve;
import javafx.fxml.FXMLLoader;

public class PageModifier extends Page{

    private ApplicationJO application;

    public PageModifier(ApplicationJO application, TypeModification type) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        this.application = application;
        FXMLLoader loader;
        Controleur controleur;
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        if(type == TypeModification.ATHLETE){
            loader = new FXMLLoader(getClass().getResource("/ModifierAthlete.fxml"));
            controleur = new ControleurModifAthlete(application);
        }else{
            loader = new FXMLLoader(getClass().getResource("/ModifierEpreuve.fxml"));
            controleur = new ControleurModifEpreuve(application);
        }
        loader.setController(controleur);
        loader.setRoot(this);
        loader.load();
        if(type == TypeModification.ATHLETE){
            ((ControleurModifAthlete) controleur).init(ApplicationJO.PROVIDER.getManager().getAthletes());
        }else{
            ((ControleurModifEpreuve) controleur).init(ApplicationJO.PROVIDER.getManager().getEpreuves());
        }
    }
    
    public enum TypeModification {
        ATHLETE(),
        EPREUVE();
    }
}
