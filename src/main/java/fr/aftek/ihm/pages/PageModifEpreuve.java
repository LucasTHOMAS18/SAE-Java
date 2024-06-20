package fr.aftek.ihm.pages;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

import fr.aftek.ihm.*;
import fr.aftek.ihm.controleurs.ControleurModifEpreuve;

public class PageModifEpreuve extends Page{
    private ApplicationJO application;

    /**
     * Constructeur de la classe PageModifEpreuve.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageModifEpreuve(ApplicationJO application) throws IOException {
        this.application = application;
        
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEpreuve.fxml"));
        ControleurModifEpreuve controleurModifEpreuve = new ControleurModifEpreuve(application);
        loader.setController(controleurModifEpreuve);
        loader.setRoot(this);
        loader.load();
    }
}