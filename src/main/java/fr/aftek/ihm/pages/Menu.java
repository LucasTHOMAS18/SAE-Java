package fr.aftek.ihm.pages;

import java.io.IOException;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Classe Menu qui étend BorderPane.
 * Affiche le menu principal de l'application.
 */
public class Menu extends BorderPane {
    private ApplicationJO application;

    /**
     * Constructeur de la classe Menu.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public Menu(ApplicationJO application) throws IOException {
        this.application = application;
        
        // Charge le fichier FXML pour le menu et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageBienvenueScrollBar.fxml"));
        loader.setController(new ControleurMenu(application));
        loader.setRoot(this);
        loader.load();
    }


    public void btnAthletes() {
        // TODO
    }

    public void btnEpreuves() {
        // TODO
    }

    public void btnEquipes() {
        // TODO
    }

    public void btnPays() {
        // TODO
    }

    public void btnAdmin() {
        // TODO
    }
}
