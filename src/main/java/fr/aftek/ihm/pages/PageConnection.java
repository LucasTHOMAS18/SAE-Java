package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.data.ConnexionMySQL;
import fr.aftek.ihm.controleurs.ControleurConnexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageConnection extends BorderPane {
    private ConnexionMySQL connexion;

    public PageConnection(ConnexionMySQL connexion) throws IOException {
        this.connexion = connexion;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageConnexion.fxml"));
        loader.setController(new ControleurConnexion(connexion));
        loader.setRoot(this);
        loader.load();
    }
}
