package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.Collection;

import fr.aftek.Epreuve;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurClassementEpreuve;
import javafx.fxml.FXMLLoader;

public class PageClassementEpreuve extends Page{
    private ControleurClassementEpreuve controleurClassementEpreuve;

    /**
     * Constructeur de la classe PageClassementEpreuve.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageClassementEpreuve(ApplicationJO application, Collection<Epreuve> liste, boolean orga) throws IOException{
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassementEpreuves.fxml"));
        controleurClassementEpreuve = new ControleurClassementEpreuve(application, orga);
        loader.setController(controleurClassementEpreuve);
        loader.setRoot(this);
        loader.load();
        controleurClassementEpreuve.init(liste);
    }
}