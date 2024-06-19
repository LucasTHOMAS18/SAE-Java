package fr.aftek.ihm.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PageAdmin extends BorderPane {

    public PageAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/aftek/ihm/fxml/PageAdmin.fxml"));
        loader.setRoot(this);
        loader.setController(new fr.aftek.ihm.controleurs.ControleurAdmin());
        loader.load();
    }
}
