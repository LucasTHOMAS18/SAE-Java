package fr.aftek.ihm.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

/**
 * Classe PageAdmin qui étend BorderPane.
 * Affiche la page d'administration de l'application.
 */
public class PageAdmin extends BorderPane {

    /**
     * Constructeur de la classe PageAdmin.
     * 
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/aftek/ihm/fxml/PageAdmin.fxml"));
        loader.setRoot(this);
        loader.setController(new fr.aftek.ihm.controleurs.ControleurAdmin());
        loader.load();
    }


}


