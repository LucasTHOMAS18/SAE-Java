package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurConnexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageConnexion extends BorderPane {
    public PageConnexion(ApplicationJO application) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageConnexion.fxml"));
        loader.setController(new ControleurConnexion(application));
        loader.setRoot(this);
        loader.load();
    }
}
