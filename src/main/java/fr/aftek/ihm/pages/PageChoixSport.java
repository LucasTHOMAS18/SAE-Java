package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.ihm.controleurs.ControleurChoixSport;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageChoixSport extends BorderPane{
    public PageChoixSport() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChoixSport.fxml"));
        ControleurChoixSport controleurChoixSport = new ControleurChoixSport();
        loader.setController(controleurChoixSport);
        loader.setRoot(this);
        loader.load();
        controleurChoixSport.init();
    }
}
