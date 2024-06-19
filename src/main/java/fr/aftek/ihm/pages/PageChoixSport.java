package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurChoixSport;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageChoixSport extends BorderPane{
    private ApplicationJO application;
    public PageChoixSport(ApplicationJO application) throws IOException {
        this.application = application;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChoixSport.fxml"));
        ControleurChoixSport controleurChoixSport = new ControleurChoixSport();
        loader.setController(controleurChoixSport);
        loader.setRoot(this);
        loader.load();
        controleurChoixSport.init();
    }
}
