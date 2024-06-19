package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.ihm.controleurs.ControleurChoixSport;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageChoixSport extends BorderPane{
    public PageChoixSport() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChoixSport.fxml"));
        loader.setController(new ControleurChoixSport());
        loader.setRoot(this);
        loader.load();
    }
}
