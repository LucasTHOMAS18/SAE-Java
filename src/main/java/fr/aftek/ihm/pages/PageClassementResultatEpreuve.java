package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.aftek.Epreuve;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurClassementEpreuve;
import fr.aftek.ihm.controleurs.ControleurClassementResultatEpreuve;
import javafx.fxml.FXMLLoader;

public class PageClassementResultatEpreuve extends Page{
    private ControleurClassementResultatEpreuve controleurClassementResultatEpreuve;

    /**
     * Constructeur de la classe PageClassementResultatEpreuve.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @param epreuve L'épreuve dont on veut afficher le classement.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageClassementResultatEpreuve(ApplicationJO application, Epreuve epreuve) throws IOException{
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassementResultatEpreuve.fxml"));
        controleurClassementResultatEpreuve = new ControleurClassementResultatEpreuve(application, epreuve);
        loader.setController(controleurClassementResultatEpreuve);
        loader.setRoot(this);
        loader.load();
        controleurClassementResultatEpreuve.init(epreuve);
    }

}