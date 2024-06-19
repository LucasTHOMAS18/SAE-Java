package fr.aftek.ihm.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import fr.aftek.ihm.*;
import fr.aftek.ihm.controleurs.ControleurModifAthlete;

/**
 * Classe PageModifAthlete qui étend BorderPane.
 * Affiche la page de choix de sport de l'application.
 */
public class PageModifAthlete extends BorderPane {
    
    private ApplicationJO application;

    /**
     * Constructeur de la classe PageModifAthlete.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageModifAthlete(ApplicationJO application) throws IOException {
        this.application = application;
        
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierAthlete.fxml"));
        ControleurModifAthlete controleurModifAthlete = new ControleurModifAthlete(application);
        loader.setController(controleurModifAthlete);
        loader.setRoot(this);
        loader.load();
    }
}