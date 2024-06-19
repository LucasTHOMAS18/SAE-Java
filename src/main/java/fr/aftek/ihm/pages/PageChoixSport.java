package fr.aftek.ihm.pages;

import java.io.IOException;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurChoixSport;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Classe PageChoixSport qui étend BorderPane.
 * Affiche la page de choix de sport de l'application.
 */
public class PageChoixSport extends BorderPane {
    
    private ApplicationJO application;

    /**
     * Constructeur de la classe PageChoixSport.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageChoixSport(ApplicationJO application) throws IOException {
        this.application = application;
        
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChoixSport.fxml"));
        ControleurChoixSport controleurChoixSport = new ControleurChoixSport();
        loader.setController(controleurChoixSport);
        loader.setRoot(this);
        loader.load();
        controleurChoixSport.init();
    }
}
