package fr.aftek.ihm.pages;

import java.io.IOException;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurConnexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Classe PageConnexion qui étend BorderPane.
 * Cette classe est utilisée pour afficher la page de connexion de l'application.
 */
public class PageConnexion extends BorderPane {

    /**
     * Constructeur de la classe PageConnexion.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML
     */
    public PageConnexion(ApplicationJO application) throws IOException {
        // Charge le fichier FXML pour la page de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageConnexion.fxml"));
        
        // Définit le contrôleur de la page de connexion
        loader.setController(new ControleurConnexion(application));
        
        // Définit la racine du FXML à ce BorderPane
        loader.setRoot(this);
        
        // Charge le fichier FXML
        loader.load();
    }
}
